package com.shaheed.portal.controller;

import com.shaheed.portal.model.User;
import com.shaheed.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

/**
 * AuthController — Login / Logout / Password Change
 *
 * URL Mappings:
 * GET  /login          → Login page dikhao
 * POST /login          → Credentials check karo
 * GET  /logout         → Session destroy, login page pe bhejo
 * GET  /change-password → Password change form
 * POST /change-password → Password update karo
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ==================== LOGIN ====================

    @GetMapping("/login")
    public String showLogin(HttpSession session) {
        // Already logged in? Redirect to their dashboard
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            return getDashboardRedirect(user.getRole());
        }
        return "common/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               RedirectAttributes ra) {
        try {
            User user = userService.login(username.trim(), password);

            if (user == null) {
                ra.addFlashAttribute("errorMsg", "Username ya password galat hai");
                return "redirect:/login";
            }

            if (!user.isActive()) {
                ra.addFlashAttribute("errorMsg", "Aapka account deactivate kar diya gaya hai. Admin se contact karein.");
                return "redirect:/login";
            }

            // Session mein user store karo
            session.setAttribute("loggedUser", user);
            session.setAttribute("userRole",   user.getRole().name());
            session.setAttribute("userName",   user.getFullName());
            session.setAttribute("userId",     user.getUserId());

            // Pehli baar login? Password change page pe bhejo
            if (!user.isPasswordChanged() &&
                (user.getRole() == User.Role.OFFICER || user.getRole() == User.Role.FAMILY)) {
                ra.addFlashAttribute("firstLogin", true);
                return "redirect:/change-password";
            }

            // Role ke hisaab se dashboard
            return getDashboardRedirect(user.getRole());

        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Login mein problem aayi. Dobara try karein.");
            return "redirect:/login";
        }
    }

    // ==================== LOGOUT ====================

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes ra) {
        session.invalidate();
        ra.addFlashAttribute("successMsg", "Aap successfully logout ho gaye hain.");
        return "redirect:/login";
    }

    // ==================== CHANGE PASSWORD ====================

    @GetMapping("/change-password")
    public String showChangePassword(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        return "common/change-password";
    }

    @PostMapping("/change-password")
    public String processChangePassword(@RequestParam("oldPassword") String oldPass,
                                        @RequestParam("newPassword") String newPass,
                                        @RequestParam("confirmPassword") String confirmPass,
                                        HttpSession session,
                                        RedirectAttributes ra) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        if (!newPass.equals(confirmPass)) {
            ra.addFlashAttribute("errorMsg", "Naya password aur confirm password match nahi kar rahe");
            return "redirect:/change-password";
        }

        if (newPass.length() < 6) {
            ra.addFlashAttribute("errorMsg", "Password kam se kam 6 characters ka hona chahiye");
            return "redirect:/change-password";
        }

        try {
            userService.changePassword(user.getUserId(), oldPass, newPass);

            // Session mein updated user refresh karo
            User updatedUser = userService.getUserById(user.getUserId());
            session.setAttribute("loggedUser", updatedUser);

            ra.addFlashAttribute("successMsg", "Password successfully change ho gaya!");
            return getDashboardRedirect(user.getRole());
        } catch (RuntimeException e) {
            ra.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/change-password";
        }
    }

    // ==================== HOME / INDEX ====================

    @GetMapping({"/", "/home"})
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) return getDashboardRedirect(user.getRole());
        return "common/home";
    }

    // ==================== UTILITY ====================

    /**
     * Role ke hisaab se dashboard URL return karta hai
     * ADMIN   → /admin/dashboard
     * OFFICER → /officer/dashboard
     * FAMILY  → /family/dashboard
     */
    private String getDashboardRedirect(User.Role role) {
        switch (role) {
            case ADMIN:   return "redirect:/admin/dashboard";
            case OFFICER: return "redirect:/officer/dashboard";
            case FAMILY:  return "redirect:/family/dashboard";
            default:      return "redirect:/login";
        }
    }

    // ==================== ACCESS DENIED ====================

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "common/error403";
    }
}
