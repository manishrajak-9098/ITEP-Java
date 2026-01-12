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
	    
	    String email = req.getParameter("email");

	  
	    if (UserDao.isEmailExist(email)) {
	     
	        res.sendRedirect("register.jsp?error=2");
	        return;
	    }

	   
	    User u = new User();
	    u.setName(req.getParameter("name"));
	    u.setEmail(email);
	    u.setPassword(req.getParameter("password"));
	    u.setRole(req.getParameter("role"));

	    int status = UserDao.register(u);
	    if (status > 0) 
	        res.sendRedirect("login.jsp?msg=registered");
	    else 
	        res.sendRedirect("register.jsp?error=1");
	}
}