package com.project;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        HttpSession session = req.getSession();

        // Admin Check (Hardcoded)
        if ("admin@admin.com".equals(email) && "admin123".equals(pass)) {
            session.setAttribute("email", email);
            session.setAttribute("username", "Administrator");
            session.setAttribute("role", "admin");
            res.sendRedirect("AdminDashboardServlet"); // Dashboard par bhejo
            return;
        }

        // User Check
        String[] user = new UserDAO().login(email, pass);
        if (user != null) {
            session.setAttribute("username", user[0]);
            session.setAttribute("email", user[1]);
            session.setAttribute("role", "user");
            res.sendRedirect("home.jsp");
        } else {
            session.setAttribute("msg", "Invalid Credentials");
            session.setAttribute("msgType", "error");
            res.sendRedirect("login.jsp");
        }
    }
}