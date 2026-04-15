package com.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password")); // In real app, hash this!
        user.setDob(req.getParameter("dob"));
        user.setAddress(req.getParameter("address"));
        user.setGender(req.getParameter("gender"));

        UserDAO dao = new UserDAO();
        int status = dao.register(user);
        
        HttpSession session = req.getSession();

        if (status == 1) {
            session.setAttribute("msg", "Registration Successful! Please Login.");
            session.setAttribute("msgType", "success");
            res.sendRedirect("login.jsp");
        } else if (status == 2) {
            session.setAttribute("msg", "Email already registered.");
            session.setAttribute("msgType", "error");
            res.sendRedirect("register.jsp");
        } else {
            session.setAttribute("msg", "Server Error. Try again.");
            session.setAttribute("msgType", "error");
            res.sendRedirect("register.jsp");
        }
    }
}