package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.FoodDao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ClaimFoodServlet")
public class ClaimFoodServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int foodId = Integer.parseInt(req.getParameter("id"));
        FoodDao.updateStatus(foodId, "CLAIMED");

        res.sendRedirect("ViewFoodServlet");
    }
}
