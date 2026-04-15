package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.FoodDao;
import com.jsp.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ClaimFoodServlet")
public class ClaimFoodServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        
        if (u != null && "NGO".equals(u.getRole())) {
            int foodId = Integer.parseInt(req.getParameter("id"));
            int userId = u.getUserId();
            
            // Check if donor is same
            if(FoodDao.isDonorSameAsClaimer(foodId, userId)) {
                res.sendRedirect("ViewFoodServlet?error=ownfood");
                return;
            }

            FoodDao.claimFood(foodId, userId);
            res.sendRedirect("ViewFoodServlet?msg=claimed");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}