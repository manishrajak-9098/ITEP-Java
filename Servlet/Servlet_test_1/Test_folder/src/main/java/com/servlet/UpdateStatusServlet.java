package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.servlet.connection.GetConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = GetConnection.getConnect();
            PreparedStatement ps =
                    con.prepareStatement("update task set status='Completed' where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("view");
    }
}
