package com.jsp.controller;

import java.io.IOException;

import com.jsp.dao.FoodDao;
import com.jsp.model.Food;
import com.jsp.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddFoodServlet")
public class AddFoodServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");

        if (u == null || !"DONOR".equals(u.getRole())) {
            res.sendRedirect("login.jsp");
            return;
        }

        Food f = new Food();
        f.setDonorId(u.getUserId());
        f.setFoodName(req.getParameter("foodName"));
        f.setQuantity(req.getParameter("quantity"));
        f.setPlaceName(req.getParameter("placeName"));
        f.setFullAddress(req.getParameter("fullAddress"));
        f.setPickupTime(req.getParameter("pickupTime"));
        f.setNote(req.getParameter("note"));

        FoodDao.addFood(f);

        res.sendRedirect("donarDashboard.jsp?msg=added");
    }
}
