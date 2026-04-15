package com.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // 1. SECURITY CHECK: Check karna ki user logged in hai ya nahi
        HttpSession sess = req.getSession(false); // false matlab naya session mat banao
        
        if(sess == null || sess.getAttribute("email") == null) { 
            // Agar login nahi hai toh login page par bhej do
            res.sendRedirect("login.jsp"); 
            return; 
        }
        
        // 2. Form Parameters ko get karna
        String itemName = req.getParameter("itemName");
        String location = req.getParameter("location");
        String itemDate = req.getParameter("itemDate");
        String contact = req.getParameter("contact");
        String status = req.getParameter("status");
        String description = req.getParameter("description");

        // 3. Item Object banana aur details set karna
        Item i = new Item();
        
        /* 
           SECURITY BEST PRACTICE: 
           Username aur Email humesha SESSION se hi lena chahiye, 
           taaki koi doosra user kisi aur ke naam se post na kar sake.
        */
        i.setUsername(sess.getAttribute("username").toString());
        i.setEmail(sess.getAttribute("email").toString());
        
        i.setItemName(itemName);
        i.setLocation(location);
        i.setItemDate(itemDate); // HTML datetime-local format: "YYYY-MM-DDTHH:MM"
        i.setContact(contact);
        i.setStatus(status);
        i.setDescription(description);
        
        // 4. Database mein save karne ke liye DAO call karna
        ItemDAO dao = new ItemDAO();
        boolean isAdded = dao.addItem(i);
        
        // 5. Feedback Message set karna (Optional)
        if(isAdded) {
            sess.setAttribute("msg", "Item reported successfully!");
            sess.setAttribute("msgType", "success");
        } else {
            sess.setAttribute("msg", "Something went wrong on server.");
            sess.setAttribute("msgType", "error");
        }
        
        // 6. Redirect karna View page par taaki user apna item dekh sake
        res.sendRedirect("ViewItemServlet");
    }

    // Protection: Agar koi GET request se is URL par aane ki koshish kare
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("addItem.jsp");
    }
}