package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.UserDao;
import com.jsp.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        
        // Agar pehle se koi logged in hai, toh direct bhej do dashboard par
        if (session.getAttribute("user") != null) {
            User existingUser = (User) session.getAttribute("user");
            redirectUser(existingUser, res);
            return;
        }

        User u = UserDao.login(req.getParameter("email"), req.getParameter("password"));

        if (u != null) {
            session.setAttribute("user", u);
            redirectUser(u, res);
        } else {
            res.sendRedirect("login.jsp?error=invalid");
        }
    }

    private void redirectUser(User u, HttpServletResponse res) throws IOException {
        if ("ADMIN".equals(u.getRole())) res.sendRedirect("AdminServlet");
        else if ("DONOR".equals(u.getRole())) res.sendRedirect("donorDashboard.jsp");
        else if ("NGO".equals(u.getRole())) res.sendRedirect("ViewFoodServlet");
        else res.sendRedirect("select_role.jsp");
    }
}