package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.UserDao;
import com.jsp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        User u = UserDao.login(email, pass);
        if (u != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);

            if (u.getRole().equals("DONOR")) {
                res.sendRedirect("donarDashboard.jsp");
            } else {
               
                res.sendRedirect("ViewFoodServlet");
            }
        } else {
            res.sendRedirect("login.jsp?error=invalid");
        }
    }
}