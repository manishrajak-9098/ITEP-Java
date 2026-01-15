package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.FoodDao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CollectFoodServlet")
public class CollectFoodServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int foodId = Integer.parseInt(req.getParameter("id"));
        FoodDao.updateStatus(foodId, "COLLECTED");

        res.sendRedirect("ViewFoodServlet");
    }
}
