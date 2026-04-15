package com.project;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // SECURITY: Agar admin nahi hai toh bahar pheko
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            res.sendRedirect("login.jsp");
            return;
        }

        ItemDAO dao = new ItemDAO();
        Map<String, Integer> stats = dao.getStats();
        List<Item> allItems = dao.getAllItems(null);

        // Data ko request mein set karo
        req.setAttribute("stats", stats);
        req.setAttribute("allItems", allItems);
        
        // admin.jsp par forward karo (Prefix hatakar)
        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }
}