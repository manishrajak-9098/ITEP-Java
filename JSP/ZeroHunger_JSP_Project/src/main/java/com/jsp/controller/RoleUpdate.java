package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.UserDao;
import com.jsp.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RoleUpdateServlet")
public class RoleUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        
        if (u != null) {
            String selectedRole = req.getParameter("roleChoice"); // DONOR or NGO
            
            // 1. Database update karo (taaki record rahe ki current status kya hai)
            UserDao.updateRole(u.getUserId(), selectedRole);
            
            // 2. Session Object update karo (Isse Dashboard access milega)
            u.setRole(selectedRole);
            
            // 3. Redirect karo
            if("DONOR".equals(selectedRole)) {
                res.sendRedirect("donorDashboard.jsp");
            } else {
                res.sendRedirect("ViewFoodServlet");
            }
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}

