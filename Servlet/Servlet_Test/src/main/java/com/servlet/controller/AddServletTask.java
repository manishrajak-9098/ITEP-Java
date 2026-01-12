
package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import com.servlet.dao.TaskDao;
import com.servlet.dto.Task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddServletTask extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String category = request.getParameter("category");
        String task = request.getParameter("task");
        String priority = request.getParameter("priority");

        Task tk = new Task();
        tk.setCategory(category);
        tk.setTask(task);
        tk.setPriority(priority);
    	TaskDao dao = new TaskDao();
		int i = dao.addTask(tk);
		
		if(i==1) {
		    System.out.println("User added successfully");
		    out.print("<script>alert('User added successfully')</script>");
		} else {
		    System.out.println("Error while Adding User");
		    out.print("<script>alert('Error while Adding User')</script>");
		}

        
        
        out.println("<h2>Task Added Successfully</h2>");
        out.println("Category: " + category + "<br>");
        out.println("Task: " + task + "<br>");
        out.println("Priority: " + priority + "<br>");
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
