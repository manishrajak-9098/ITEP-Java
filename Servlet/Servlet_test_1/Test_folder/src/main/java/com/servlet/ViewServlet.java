package com.servlet;

import dao.TaskDao;
import dto.Task;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        process(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        process(req, res);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        TaskDao dao = new TaskDao();
        List<Task> list = dao.getAllTask();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Task List</h2>");

        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>Category</th>");
        out.println("<th>Task</th>");
        out.println("<th>Priority</th>");
        out.println("<th>Status</th>");
        out.println("<th>Update</th>");
        out.println("<th>Delete</th>");
        out.println("</tr>");

        for (Task t : list) {
            out.println("<tr>");
            out.println("<td>" + t.getCategory() + "</td>");
            out.println("<td>" + t.getTask() + "</td>");
            out.println("<td>" + t.getPriority() + "</td>");
            out.println("<td>" + t.getStatus() + "</td>");

            if ("Pending".equals(t.getStatus())) {
                out.println("<td><a href='updateStatus?id=" + t.getId() + "'>Pending</a></td>");
            } else {
                out.println("<td>Completed</td>");
            }

            out.println("<td><a href='deleteTask?id=" + t.getId()
                    + "' onclick=\"return confirm('Delete task?')\">Delete</a></td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><a href='index.html'>Back</a>");
        out.println("</body></html>");
    }
}
