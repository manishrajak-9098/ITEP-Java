package com.shaheed.portal.controller;

import com.shaheed.portal.model.*;
import com.shaheed.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * OfficerController — Document verification & case management
 *
 * Mappings:
 * /officer/dashboard         → Stats + assigned cases
 * /officer/cases             → All assigned soldiers
 * /officer/case/{id}         → Case details + documents
 * /officer/documents/pending → Pending documents
 * /officer/document/verify   → Verify/reject document
 * /officer/grievances        → View grievances
 */
@Controller
@RequestMapping("/officer")
public class OfficerController {

    @Autowired private SoldierService  soldierService;
    @Autowired private DocumentService documentService;
    @Autowired private BenefitService  benefitService;
    @Autowired private GrievanceService grievanceService;

    // ==================== DASHBOARD ====================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isOfficer(session)) return "redirect:/access-denied";

        User officer = (User) session.getAttribute("loggedUser");
        int officerId = officer.getUserId();

        List<Soldier> myCases = soldierService.getSoldiersForOfficer(officerId);

        long pendingVerification = myCases.stream()
                .filter(s -> s.getCaseStatus() == Soldier.CaseStatus.UNDER_VERIFICATION)
                .count();
        long verified = myCases.stream()
                .filter(s -> s.getCaseStatus() == Soldier.CaseStatus.VERIFIED)
                .count();

        model.addAttribute("totalAssigned",      myCases.size());
        model.addAttribute("pendingVerification", pendingVerification);
        model.addAttribute("verifiedCases",       verified);
        model.addAttribute("pendingDocs",         documentService.getPendingCount());
        model.addAttribute("openGrievances",      grievanceService.getOpenCount());
        model.addAttribute("recentCases",         myCases.size() > 5 ? myCases.subList(0, 5) : myCases);

        return "officer/dashboard";
    }

    // ==================== ASSIGNED CASES ====================

    @GetMapping("/cases")
    public String myCases(HttpSession session, Model model) {
        if (!isOfficer(session)) return "redirect:/access-denied";

        User officer = (User) session.getAttribute("loggedUser");
        List<Soldier> cases = soldierService.getSoldiersForOfficer(officer.getUserId());
        model.addAttribute("cases", cases);
        return "officer/cases";
    }

    @GetMapping("/case/{id}")
    public String viewCase(@PathVariable int id,
                           HttpSession session, Model model) {
        if (!isOfficer(session)) return "redirect:/access-denied";

        Soldier soldier = soldierService.getSoldierById(id);
        if (soldier == null) return "redirect:/officer/cases";

        // Officer sirf apna assigned case dekhe
        User officer = (User) session.getAttribute("loggedUser");
        if (soldier.getAssignedOfficerId() == null ||
            soldier.getAssignedOfficerId() != officer.getUserId()) {
            return "redirect:/access-denied";
        }

        model.addAttribute("soldier",   soldier);
        model.addAttribute("documents", documentService.getDocumentsBySoldier(id));
        model.addAttribute("benefits",  benefitService.getBenefitsBySoldier(id));
        model.addAttribute("statuses",  Soldier.CaseStatus.values());
        return "officer/case-detail";
    }

    // ==================== CASE STATUS UPDATE ====================

    @PostMapping("/case/{id}/update-status")
    public String updateCaseStatus(@PathVariable int id,
                                   @RequestParam String status,
                                   @RequestParam(required = false) String remarks,
                                   HttpSession session, RedirectAttributes ra) {
        if (!isOfficer(session)) return "redirect:/access-denied";

        Soldier soldier = soldierService.getSoldierById(id);
        User officer = (User) session.getAttribute("loggedUser");

        if (soldier.getAssignedOfficerId() == null ||
            soldier.getAssignedOfficerId() != officer.getUserId()) {
            return "redirect:/access-denied";
        }

        soldierService.updateCaseStatus(id, Soldier.CaseStatus.valueOf(status));

        if (remarks != null && !remarks.isEmpty()) {
            soldier.setOfficerRemarks(remarks);
            soldierService.updateSoldier(soldier);
        }

        ra.addFlashAttribute("successMsg", "Case status update ho gaya: " + status);
        return "redirect:/officer/case/" + id;
    }

    // ==================== DOCUMENT VERIFICATION ====================

    @GetMapping("/documents/pending")
    public String pendingDocuments(HttpSession session, Model model) {
        if (!isOfficer(session)) return "redirect:/access-denied";
        model.addAttribute("pendingDocs", documentService.getPendingDocuments());
        return "officer/pending-documents";
    }

    @PostMapping("/document/verify")
    public String verifyDocument(@RequestParam int docId,
                                 @RequestParam String verificationStatus,
                                 @RequestParam(required = false) String remarks,
                                 @RequestParam(required = false) Integer soldierId,
                                 HttpSession session, RedirectAttributes ra) {
        if (!isOfficer(session)) return "redirect:/access-denied";

        User officer = (User) session.getAttribute("loggedUser");
        documentService.verifyDocument(docId,
                Document.VerificationStatus.valueOf(verificationStatus),
                officer.getUserId(),
                remarks != null ? remarks : "");

        ra.addFlashAttribute("successMsg", "Document " + verificationStatus + " ho gaya.");

        if (soldierId != null) {
            return "redirect:/officer/case/" + soldierId;
        }
        return "redirect:/officer/documents/pending";
    }

    // ==================== GRIEVANCES ====================

    @GetMapping("/grievances")
    public String viewGrievances(HttpSession session, Model model) {
        if (!isOfficer(session)) return "redirect:/access-denied";
        model.addAttribute("grievances", grievanceService.getAllOpenGrievances());
        return "officer/grievances";
    }

    @PostMapping("/grievance/{id}/respond")
    public String respondGrievance(@PathVariable int id,
                                   @RequestParam String response,
                                   HttpSession session, RedirectAttributes ra) {
        if (!isOfficer(session)) return "redirect:/access-denied";
        User officer = (User) session.getAttribute("loggedUser");
        grievanceService.respondToGrievance(id, response, officer.getUserId());
        ra.addFlashAttribute("successMsg", "Grievance resolved kar diya gaya.");
        return "redirect:/officer/grievances";
    }

    // ==================== SECURITY CHECK ====================

    private boolean isOfficer(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return user != null && user.getRole() == User.Role.OFFICER;
    }
}
