package com.shaheed.portal.controller;

import com.shaheed.portal.model.Application;
import com.shaheed.portal.model.FamilyMember;
import com.shaheed.portal.service.PortalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyController {

    private final PortalService portalService;

    public FamilyController(PortalService portalService) {
        this.portalService = portalService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        FamilyMember member = (FamilyMember) session.getAttribute("user");
        if (member == null || member.isAdmin()) {
            return "redirect:/family/login";
        }
        List<Application> applications = portalService.getApplicationsForFamily(member.getId());
        model.addAttribute("applications", applications);
        return "family-dashboard";
    }

    @GetMapping("/application/new")
    public String newApplication(HttpSession session) {
        FamilyMember member = (FamilyMember) session.getAttribute("user");
        if (member == null || member.isAdmin()) {
            return "redirect:/family/login";
        }
        return "family-application-form";
    }

    @PostMapping("/application/submit")
    public String submitApplication(HttpSession session,
                                    @RequestParam String shaheedName,
                                    @RequestParam String rank,
                                    @RequestParam String unit,
                                    @RequestParam String serviceNumber,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfMartyrdom) {
        FamilyMember member = (FamilyMember) session.getAttribute("user");
        if (member == null || member.isAdmin()) {
            return "redirect:/family/login";
        }
        portalService.submitApplication(member, shaheedName, rank, unit, serviceNumber, dateOfMartyrdom);
        return "redirect:/family/dashboard";
    }
}

