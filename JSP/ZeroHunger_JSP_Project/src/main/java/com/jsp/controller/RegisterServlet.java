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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if(UserDao.isEmailExist(req.getParameter("email"))){
            res.sendRedirect("register.jsp?error=exists"); return;
        }
        User u = new User();
        u.setName(req.getParameter("name"));
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password")); 
        u.setPhone(req.getParameter("phone"));
        u.setAddress(req.getParameter("address"));
        u.setDob(req.getParameter("dob"));
        u.setGender(req.getParameter("gender"));
        // Role 'USER' DB default se lega
        
        if(UserDao.register(u) > 0) res.sendRedirect("login.jsp?msg=registered");
        else res.sendRedirect("register.jsp?error=failed");
    }
}