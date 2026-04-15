package com.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ResolveItemServlet")
public class ResolveItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // 1. Check Login
        if (session == null || session.getAttribute("email") == null) { 
            res.sendRedirect("login.jsp"); 
            return; 
        }

        try {
            // 2. Data fetch from Form
            int id = Integer.parseInt(req.getParameter("id"));
            String finderName = req.getParameter("finderName");
            String finderEmail = req.getParameter("finderEmail");
            
            // Agar form khali hai, toh logged-in user ka naam/email use kar lo 
            if(finderName == null || finderName.isEmpty()) {
                finderName = (String) session.getAttribute("username");
            }
            if(finderEmail == null || finderEmail.isEmpty()) {
                finderEmail = (String) session.getAttribute("email");
            }

            // 3. Database Update call
            ItemDAO dao = new ItemDAO();
            boolean done = dao.resolveItem(id, finderName, finderEmail);
            
            if(done) {
                session.setAttribute("msg", "Item marked as Resolved! Thanks for helping.");
            } else {
                session.setAttribute("msg", "Failed to resolve item.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Wapas list par bhejo
        res.sendRedirect("ViewItemServlet");
    }
}