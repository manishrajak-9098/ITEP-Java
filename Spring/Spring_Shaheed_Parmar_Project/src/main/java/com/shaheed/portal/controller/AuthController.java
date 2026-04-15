package com.shaheed.portal.controller;

import com.shaheed.portal.model.FamilyMember;
import com.shaheed.portal.service.PortalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AuthController {

    private final PortalService portalService;

    public AuthController(PortalService portalService) {
        this.portalService = portalService;
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/family/register")
    public String showFamilyRegister(Model model) {
        model.addAttribute("familyMember", new FamilyMember());
        return "family-register";
    }

    @PostMapping("/family/register")
    public String doFamilyRegister(@ModelAttribute FamilyMember familyMember, Model model) {
        portalService.registerFamily(familyMember);
        model.addAttribute("message", "Registration successful. Please login.");
        return "family-login";
    }

    @GetMapping("/family/login")
    public String showFamilyLogin() {
        return "family-login";
    }

    @PostMapping("/family/login")
    public String doFamilyLogin(@RequestParam String email,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        return portalService.login(email, password)
                .map(user -> {
                    session.setAttribute("user", user);
                    if (user.isAdmin()) {
                        return "redirect:/admin/dashboard";
                    }
                    return "redirect:/family/dashboard";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid credentials");
                    return "family-login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}

