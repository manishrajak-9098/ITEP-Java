package com.shaheed.portal.controller;

import com.shaheed.portal.model.*;
import com.shaheed.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * FamilyController — Shaheed ke parivaar ka portal
 *
 * Family sirf:
 * - Apna case status dekh sakti hai
 * - Documents upload kar sakti hai
 * - Benefits track kar sakti hai
 * - Grievance file kar sakti hai
 *
 * Mappings:
 * /family/dashboard        → Status overview
 * /family/benefits         → Benefits list
 * /family/documents        → Upload + view
 * /family/grievance/new    → File complaint
 * /family/grievances       → My complaints
 * /family/track            → Public case tracking
 */
@Controller
@RequestMapping("/family")
public class FamilyController {

    @Autowired private SoldierService  soldierService;
    @Autowired private BenefitService  benefitService;
    @Autowired private DocumentService documentService;
    @Autowired private GrievanceService grievanceService;

    // Document upload directory (server pe)
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/shaheed-docs/";

    // ==================== DASHBOARD ====================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";

        User familyUser = (User) session.getAttribute("loggedUser");
        int familyId = familyUser.getLinkedFamilyId() != null ? familyUser.getLinkedFamilyId() : 0;

        // Soldier dhundo is family ke liye
        // Note: In production, FamilyMemberService se soldier_id nikaalo
        // Yahan simplify kiya hai demo ke liye
        model.addAttribute("familyUser", familyUser);

        // Benefits summary
        model.addAttribute("pendingBenefits",  benefitService.getPendingCount());
        model.addAttribute("approvedBenefits", benefitService.getApprovedCount());
        model.addAttribute("paidBenefits",     benefitService.getPaidCount());

        // Grievances
        model.addAttribute("myGrievances", grievanceService.getGrievancesByFamily(familyId));
        model.addAttribute("openGrievanceCount",
                grievanceService.getGrievancesByFamily(familyId).stream()
                        .filter(g -> g.getStatus() == Grievance.GrievanceStatus.OPEN).count());

        return "family/dashboard";
    }

    // ==================== CASE STATUS (by Application ID) ====================

    @GetMapping("/case-status")
    public String caseStatus(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";
        return "family/case-status";
    }

    @PostMapping("/case-status/search")
    public String searchCase(@RequestParam String applicationId,
                             HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";

        Soldier soldier = soldierService.getSoldierByApplicationId(applicationId.trim().toUpperCase());
        if (soldier != null) {
            model.addAttribute("soldier",   soldier);
            model.addAttribute("benefits",  benefitService.getBenefitsBySoldier(soldier.getSoldierId()));
            model.addAttribute("documents", documentService.getDocumentsBySoldier(soldier.getSoldierId()));
        } else {
            model.addAttribute("notFound", true);
        }
        model.addAttribute("searchId", applicationId);
        return "family/case-status";
    }

    // ==================== BENEFITS ====================

    @GetMapping("/benefits")
    public String viewBenefits(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";

        User familyUser = (User) session.getAttribute("loggedUser");
        int familyId = familyUser.getLinkedFamilyId() != null ? familyUser.getLinkedFamilyId() : 0;

        // All benefits for this family's soldier
        // Simplification: real me FamilyMember → Soldier link se nikaalenge
        model.addAttribute("familyUser", familyUser);
        return "family/benefits";
    }

    // ==================== DOCUMENTS ====================

    @GetMapping("/documents")
    public String viewDocuments(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";
        User familyUser = (User) session.getAttribute("loggedUser");
        model.addAttribute("familyUser",  familyUser);
        model.addAttribute("docTypes",    Document.DocType.values());
        return "family/documents";
    }

    @PostMapping("/document/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                 @RequestParam("docType")  String docType,
                                 @RequestParam("docName")  String docName,
                                 @RequestParam("soldierId") int soldierId,
                                 HttpSession session, RedirectAttributes ra) {
        if (!isFamily(session)) return "redirect:/access-denied";

        User familyUser = (User) session.getAttribute("loggedUser");
        int familyId    = familyUser.getLinkedFamilyId() != null ? familyUser.getLinkedFamilyId() : 0;

        try {
            documentService.uploadDocument(
                    file, soldierId, familyId,
                    Document.DocType.valueOf(docType), docName, UPLOAD_DIR);

            ra.addFlashAttribute("successMsg", "Document upload ho gaya! Officer verify karega.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Upload failed: " + e.getMessage());
        }
        return "redirect:/family/documents";
    }

    // ==================== GRIEVANCE ====================

    @GetMapping("/grievances")
    public String myGrievances(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";

        User familyUser = (User) session.getAttribute("loggedUser");
        int familyId    = familyUser.getLinkedFamilyId() != null ? familyUser.getLinkedFamilyId() : 0;

        model.addAttribute("grievances", grievanceService.getGrievancesByFamily(familyId));
        model.addAttribute("categories", Grievance.GrievanceCategory.values());
        return "family/grievances";
    }

    @GetMapping("/grievance/new")
    public String newGrievanceForm(HttpSession session, Model model) {
        if (!isFamily(session)) return "redirect:/access-denied";
        model.addAttribute("categories", Grievance.GrievanceCategory.values());
        return "family/grievance-form";
    }

    @PostMapping("/grievance/file")
    public String fileGrievance(@RequestParam String subject,
                                @RequestParam String description,
                                @RequestParam String category,
                                @RequestParam int soldierId,
                                HttpSession session, RedirectAttributes ra) {
        if (!isFamily(session)) return "redirect:/access-denied";

        User familyUser = (User) session.getAttribute("loggedUser");
        int familyId    = familyUser.getLinkedFamilyId() != null ? familyUser.getLinkedFamilyId() : 0;

        Grievance g = new Grievance();
        g.setSoldierId(soldierId);
        g.setFamilyId(familyId);
        g.setSubject(subject);
        g.setDescription(description);
        g.setCategory(Grievance.GrievanceCategory.valueOf(category));

        grievanceService.fileGrievance(g);

        ra.addFlashAttribute("successMsg",
                "Aapki complaint register ho gayi. Grievance Number: " + g.getGrievanceNumber());
        return "redirect:/family/grievances";
    }

    // ==================== PUBLIC CASE TRACK (No login required) ====================

    @GetMapping("/track")
    public String trackCase() {
        return "common/track-case";
    }

    @PostMapping("/track/search")
    public String trackSearch(@RequestParam String applicationId, Model model) {
        Soldier soldier = soldierService.getSoldierByApplicationId(applicationId.trim().toUpperCase());
        if (soldier != null) {
            model.addAttribute("soldier",  soldier);
            model.addAttribute("benefits", benefitService.getBenefitsBySoldier(soldier.getSoldierId()));
        } else {
            model.addAttribute("notFound", true);
        }
        model.addAttribute("searchId", applicationId);
        return "common/track-case";
    }

    // ==================== SECURITY CHECK ====================

    private boolean isFamily(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return user != null && user.getRole() == User.Role.FAMILY;
    }
}
