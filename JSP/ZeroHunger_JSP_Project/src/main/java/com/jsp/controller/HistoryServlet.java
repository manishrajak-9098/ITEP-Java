package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.FoodDao;
import com.jsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User u = (User) req.getSession().getAttribute("user");
        if(u == null) { res.sendRedirect("login.jsp"); return; }
        
        if ("DONOR".equals(u.getRole())) {
            req.setAttribute("list", FoodDao.getFoodByDonor(u.getUserId()));
            req.setAttribute("title", "My Donation History");
        } else if ("NGO".equals(u.getRole())) {
            req.setAttribute("list", FoodDao.getFoodByNGO(u.getUserId()));
            req.setAttribute("title", "My Claimed History");
        }
        req.getRequestDispatcher("history.jsp").forward(req, res);
    }
}