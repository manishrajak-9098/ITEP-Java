package com.project;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserHistoryServlet")
public class UserHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("email") == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String email = (String) session.getAttribute("email");
        // Logged-in email pass ho raha hai filtering ke liye
        req.setAttribute("myItems", new ItemDAO().getAllItems(email));
        req.getRequestDispatcher("history.jsp").forward(req, res);
    }
}