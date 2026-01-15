package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.UserDao;
import com.jsp.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        // ⭐ IF EMAIL ALREADY EXISTS → UPDATE ROLE
        if (UserDao.isEmailExist(email)) {
            UserDao.updateRoleByEmail(email, role);
            res.sendRedirect("login.jsp?msg=role_updated");
            return;
        }

        // USER REGISTER
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);

        int status = UserDao.register(u);
        if (status > 0) {
            res.sendRedirect("login.jsp?msg=registered");
        } else {
            res.sendRedirect("register.jsp?error=1");
        }
    }
}
