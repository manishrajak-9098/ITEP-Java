package com.app.controller;

import java.io.IOException;

import com.app.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao dao = new UserDao();

        if (dao.login(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("ProfileServlet");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
