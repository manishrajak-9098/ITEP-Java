package com.project;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewItemServlet")
public class ViewItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // CHANGE: Sirf login check karo, Role check mat karo.
        // Taaki normal user bhi doosron ke lost items dekh sake.
        if (session == null || session.getAttribute("email") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // DAO se saare items lao
        ItemDAO dao = new ItemDAO();
        List<Item> allItems = dao.getAllItems(null); // null matlab sab kuch lao

        req.setAttribute("allItems", allItems);
        
        // viewItems.jsp par bhejo
        req.getRequestDispatcher("viewItems.jsp").forward(req, res);
    }
}