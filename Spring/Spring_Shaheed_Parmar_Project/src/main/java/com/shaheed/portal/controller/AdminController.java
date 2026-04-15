package com.shaheed.portal.controller;

import com.shaheed.portal.model.Application;
import com.shaheed.portal.model.FacilityType;
import com.shaheed.portal.model.FamilyMember;
import com.shaheed.portal.service.PortalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PortalService portalService;

    public AdminController(PortalService portalService) {
        this.portalService = portalService;
    }

    private boolean isAdmin(HttpSession session) {
        FamilyMember user = (FamilyMember) session.getAttribute("user");
        return user != null && user.isAdmin();
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/family/login";
        }
        model.addAttribute("total", portalService.countTotalApplications());
        model.addAttribute("pending", portalService.countPending());
        model.addAttribute("verified", portalService.countVerified());
        model.addAttribute("rejected", portalService.countRejected());
        model.addAttribute("applications", portalService.getAllApplications());
        return "admin-dashboard";
    }

    @GetMapping("/application/{id}")
    public String viewApplication(@PathVariable Long id, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/family/login";
        }
        Application application = portalService.getApplication(id).orElse(null);
        if (application == null) {
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("application", application);
        model.addAttribute("facilityTypes", Arrays.asList(FacilityType.values()));
        return "admin-application-detail";
    }

    @PostMapping("/application/{id}/verify")
    public String verify(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/family/login";
        }
        portalService.verifyApplication(id);
        return "redirect:/admin/application/" + id;
    }

    @PostMapping("/application/{id}/reject")
    public String reject(@PathVariable Long id,
                         @RequestParam String reason,
                         HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/family/login";
        }
        portalService.rejectApplication(id, reason);
        return "redirect:/admin/application/" + id;
    }

    @PostMapping("/application/{id}/facility")
    public String allocateFacility(@PathVariable Long id,
                                   @RequestParam FacilityType facilityType,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                   @RequestParam String remarks,
                                   HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/family/login";
        }
        portalService.allocateFacility(id, facilityType, startDate, remarks);
        return "redirect:/admin/application/" + id;
    }
}

