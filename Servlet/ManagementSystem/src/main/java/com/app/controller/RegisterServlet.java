package com.app.controller;

import java.io.IOException;

import com.app.dao.UserDao;
import com.app.dto.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        user.setName(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));

        UserDao dao = new UserDao();

        if (dao.addUser(user) == 1) {
            response.sendRedirect("login.html");
        } else {
            response.sendRedirect("register.html");
        }
    }
}
