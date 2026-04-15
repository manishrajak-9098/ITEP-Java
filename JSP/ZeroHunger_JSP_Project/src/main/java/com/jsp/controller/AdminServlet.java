package com.jsp.controller;

import java.io.IOException;
import com.jsp.dao.*;
import com.jsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        User u = (User) req.getSession().getAttribute("user");

        // Session + Role Check
        if (u == null || !"ADMIN".equals(u.getRole())) {
            res.sendRedirect("login.jsp");
            return;
        }

        // Data for Admin Dashboard
        req.setAttribute("users", UserDao.getAllUsers());
        req.setAttribute("foods", FoodDao.getAllFoodAdmin());
        req.setAttribute("feedbacks", FeedbackDao.getAllFeedbacks());

        // Forward to JSP
        req.getRequestDispatcher("adminDashboard.jsp").forward(req, res);
    }
}
