package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.FoodDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewFoodServlet")
public class ViewFoodServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setAttribute("list", FoodDao.getAvailableFood());
        req.getRequestDispatcher("ngoDashboard.jsp").forward(req, res);
    }
}
