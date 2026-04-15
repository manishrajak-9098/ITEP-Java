package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.FoodDao;
import com.jsp.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AddFoodServlet")
public class AddFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u != null && "DONOR".equals(u.getRole())) {
            Food f = new Food();
            f.setDonorId(u.getUserId()); 
            f.setFoodName(req.getParameter("foodName"));
            f.setQuantity(req.getParameter("quantity"));
            f.setPlaceName(req.getParameter("placeName"));
            f.setFullAddress(req.getParameter("fullAddress"));
            f.setContactNo(req.getParameter("contact_no"));
            f.setPickupTime(req.getParameter("pickupTime")); 
            f.setNote(req.getParameter("note"));
            
            FoodDao.addFood(f);
            res.sendRedirect("donorDashboard.jsp?msg=added"); // Fixed spelling 'o'
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}