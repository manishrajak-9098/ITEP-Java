package com.shaheed.portal.controller;

import com.shaheed.portal.model.*;
import com.shaheed.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * AdminController — Admin ka poora dashboard
 *
 * Sirf ADMIN role access kar sakta hai
 * Har method pe session check hota hai
 *
 * Mappings:
 * /admin/dashboard         → Main stats
 * /admin/soldiers          → All soldiers list
 * /admin/soldier/add       → New soldier form
 * /admin/soldier/{id}      → Soldier details
 * /admin/officers          → Officer management
 * /admin/officer/create    → Create officer
 * /admin/benefits          → Benefits management
 * /admin/benefit/add       → Add benefit
 * /admin/grievances        → All grievances
 * /admin/reports           → Reports page
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserService    userService;
    @Autowired private SoldierService soldierService;
    @Autowired private BenefitService benefitService;
    @Autowired private DocumentService documentService;
    @Autowired private GrievanceService grievanceService;

    // ==================== DASHBOARD ====================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";

        // Stats cards ke liye counts
        model.addAttribute("totalSoldiers",    soldierService.getTotalCount());
        model.addAttribute("pendingCases",     soldierService.getPendingCount());
        model.addAttribute("verifiedCases",    soldierService.getVerifiedCount());
        model.addAttribute("completedCases",   soldierService.getCompletedCount());
        model.addAttribute("totalOfficers",    userService.getTotalOfficerCount());
        model.addAttribute("totalFamilies",    userService.getTotalFamilyCount());
        model.addAttribute("pendingBenefits",  benefitService.getPendingCount());
        model.addAttribute("paidBenefits",     benefitService.getPaidCount());
        model.addAttribute("pendingDocs",      documentService.getPendingCount());
        model.addAttribute("openGrievances",   grievanceService.getOpenCount());

        // Recent soldiers — last 5
        List<Soldier> recentSoldiers = soldierService.getSoldiersByStatus(Soldier.CaseStatus.PENDING);
        model.addAttribute("recentSoldiers", recentSoldiers.size() > 5 ?
                recentSoldiers.subList(0, 5) : recentSoldiers);

        return "admin/dashboard";
    }

    // ==================== SOLDIER MANAGEMENT ====================

    @GetMapping("/soldiers")
    public String listSoldiers(HttpSession session, Model model,
                               @RequestParam(required = false) String status) {
        if (!isAdmin(session)) return "redirect:/access-denied";

        List<Soldier> soldiers;
        if (status != null && !status.isEmpty()) {
            try {
                soldiers = soldierService.getSoldiersByStatus(Soldier.CaseStatus.valueOf(status));
                model.addAttribute("filterStatus", status);
            } catch (Exception e) {
                soldiers = soldierService.getAllSoldiers();
            }
        } else {
            soldiers = soldierService.getAllSoldiers();
        }

        model.addAttribute("soldiers", soldiers);
        model.addAttribute("allStatuses", Soldier.CaseStatus.values());
        return "admin/soldiers";
    }

    @GetMapping("/soldier/add")
    public String showAddSoldier(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("soldier", new Soldier());
        model.addAttribute("categories", Soldier.MartydomCategory.values());
        model.addAttribute("officers", userService.getAllOfficers());
        return "admin/soldier-form";
    }

    @PostMapping("/soldier/save")
    public String saveSoldier(@ModelAttribute Soldier soldier,
                              HttpSession session,
                              RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";

        User admin = (User) session.getAttribute("loggedUser");
        try {
            soldierService.registerSoldier(soldier, admin.getUserId());
            ra.addFlashAttribute("successMsg",
                    "Shaheed " + soldier.getFullName() + " ka record save ho gaya. Application ID: "
                    + soldier.getApplicationId());
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Record save nahi hua: " + e.getMessage());
            return "redirect:/admin/soldier/add";
        }
        return "redirect:/admin/soldiers";
    }

    @GetMapping("/soldier/{id}")
    public String viewSoldier(@PathVariable int id,
                              HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";

        Soldier soldier = soldierService.getSoldierById(id);
        if (soldier == null) return "redirect:/admin/soldiers";

        model.addAttribute("soldier",   soldier);
        model.addAttribute("benefits",  benefitService.getBenefitsBySoldier(id));
        model.addAttribute("documents", documentService.getDocumentsBySoldier(id));
        model.addAttribute("officers",  userService.getAllOfficers());
        model.addAttribute("statuses",  Soldier.CaseStatus.values());
        return "admin/soldier-detail";
    }

    @PostMapping("/soldier/{id}/assign-officer")
    public String assignOfficer(@PathVariable int id,
                                @RequestParam int officerId,
                                HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        soldierService.assignOfficer(id, officerId);
        soldierService.updateCaseStatus(id, Soldier.CaseStatus.UNDER_VERIFICATION);
        ra.addFlashAttribute("successMsg", "Officer assign kar diya gaya aur status update ho gaya.");
        return "redirect:/admin/soldier/" + id;
    }

    @PostMapping("/soldier/{id}/update-status")
    public String updateSoldierStatus(@PathVariable int id,
                                      @RequestParam String status,
                                      HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        soldierService.updateCaseStatus(id, Soldier.CaseStatus.valueOf(status));
        ra.addFlashAttribute("successMsg", "Case status update ho gaya.");
        return "redirect:/admin/soldier/" + id;
    }

    // ==================== OFFICER MANAGEMENT ====================

    @GetMapping("/officers")
    public String listOfficers(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("officers", userService.getAllOfficers());
        return "admin/officers";
    }

    @GetMapping("/officer/create")
    public String showCreateOfficer(HttpSession session) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        return "admin/officer-form";
    }

    @PostMapping("/officer/create")
    public String createOfficer(@RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam String department,
                                HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        try {
            userService.createOfficerAccount(fullName, email, phone, department);
            ra.addFlashAttribute("successMsg",
                    "Officer account create ho gaya. Login credentials email pe bheje gaye hain.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Account create nahi hua: " + e.getMessage());
        }
        return "redirect:/admin/officers";
    }

    @PostMapping("/officer/{id}/deactivate")
    public String deactivateOfficer(@PathVariable int id,
                                    HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        userService.deactivateUser(id);
        ra.addFlashAttribute("successMsg", "Officer account deactivate kar diya gaya.");
        return "redirect:/admin/officers";
    }

    // ==================== BENEFITS MANAGEMENT ====================

    @GetMapping("/benefits")
    public String listBenefits(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("allBenefits",  benefitService.getBenefitsByStatus(Benefit.BenefitStatus.PENDING));
        model.addAttribute("paidBenefits", benefitService.getBenefitsByStatus(Benefit.BenefitStatus.PAID));
        model.addAttribute("pendingCount", benefitService.getPendingCount());
        model.addAttribute("paidCount",    benefitService.getPaidCount());
        return "admin/benefits";
    }

    @GetMapping("/benefit/add")
    public String showAddBenefit(HttpSession session, Model model,
                                 @RequestParam(required = false) Integer soldierId) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("benefit",      new Benefit());
        model.addAttribute("soldiers",     soldierService.getAllSoldiers());
        model.addAttribute("benefitTypes", Benefit.BenefitType.values());
        if (soldierId != null) {
            model.addAttribute("selectedSoldierId", soldierId);
        }
        return "admin/benefit-form";
    }

    @PostMapping("/benefit/save")
    public String saveBenefit(@ModelAttribute Benefit benefit,
                              HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        User admin = (User) session.getAttribute("loggedUser");
        benefitService.addBenefit(benefit, admin.getUserId());
        ra.addFlashAttribute("successMsg", "Benefit record add ho gaya.");
        return "redirect:/admin/benefits";
    }

    @PostMapping("/benefit/{id}/update-status")
    public String updateBenefitStatus(@PathVariable int id,
                                      @RequestParam String status,
                                      @RequestParam(required = false) String remarks,
                                      HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        benefitService.updateBenefitStatus(id, Benefit.BenefitStatus.valueOf(status), remarks);
        ra.addFlashAttribute("successMsg", "Benefit status update ho gaya.");
        return "redirect:/admin/benefits";
    }

    // ==================== GRIEVANCES ====================

    @GetMapping("/grievances")
    public String listGrievances(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("grievances", grievanceService.getAllGrievances());
        model.addAttribute("openCount",  grievanceService.getOpenCount());
        return "admin/grievances";
    }

    @PostMapping("/grievance/{id}/respond")
    public String respondGrievance(@PathVariable int id,
                                   @RequestParam String response,
                                   HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        User admin = (User) session.getAttribute("loggedUser");
        grievanceService.respondToGrievance(id, response, admin.getUserId());
        ra.addFlashAttribute("successMsg", "Grievance ka response submit ho gaya.");
        return "redirect:/admin/grievances";
    }

    // ==================== REPORTS ====================

    @GetMapping("/reports")
    public String reports(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/access-denied";
        model.addAttribute("totalSoldiers",  soldierService.getTotalCount());
        model.addAttribute("pendingCases",   soldierService.getPendingCount());
        model.addAttribute("completedCases", soldierService.getCompletedCount());
        model.addAttribute("pendingBenef",   benefitService.getPendingCount());
        model.addAttribute("approvedBenef",  benefitService.getApprovedCount());
        model.addAttribute("paidBenef",      benefitService.getPaidCount());
        model.addAttribute("pendingDocs",    documentService.getPendingCount());
        model.addAttribute("openGrievances", grievanceService.getOpenCount());
        return "admin/reports";
    }

    // ==================== SECURITY CHECK ====================

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return user != null && user.getRole() == User.Role.ADMIN;
    }
}
