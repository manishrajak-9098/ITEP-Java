package com.servlet;

import java.io.IOException;
import dao.TaskDao;
import dto.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addTask")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Task t = new Task();
        t.setCategory(request.getParameter("category"));
        t.setTask(request.getParameter("task"));
        t.setPriority(request.getParameter("priority"));

        TaskDao dao = new TaskDao();
        dao.addData(t);

        response.sendRedirect("index.html");
    }
}
