package com.jsp.controller;
import java.io.IOException;
import com.jsp.dao.FeedbackDao;
import com.jsp.model.Feedback;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String message = req.getParameter("message");

        Feedback f = new Feedback();
        f.setName(name);
        f.setEmail(email);
        f.setMessage(message);

        FeedbackDao.saveFeedback(f);

        // Redirect back to contact page with success message
        res.sendRedirect("contact.jsp?msg=sent");
    }
}