import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewItemServlet")
public class ViewItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ItemDAO dao = new ItemDAO();
        ResultSet rs = dao.viewItems();

        out.println("""
        <html>
        <head>
            <title>View Items</title>
            <link rel="stylesheet" href="style.css">
            <script>
                function openPopup(id){
                    document.getElementById('itemId').value=id;
                    document.getElementById('popup').style.display='block';
                    document.getElementById('overlay').style.display='block';
                }
                function closePopup(){
                    document.getElementById('popup').style.display='none';
                    document.getElementById('overlay').style.display='none';
                }
            </script>
        </head>
        <body>
            <div id="overlay" onclick="closePopup()"></div>
            <div class="card wide-card">
                <h2>Lost & Found Items</h2>
                <div class="table-wrapper">
                    <table>
                        <tr>
                            <th>ID</th><th>User</th><th>Item</th><th>Location</th>
                            <th>Status</th><th>Contact</th><th>Description</th><th>Action</th>
                        </tr>
        """);

        try {
            while (rs.next()) {
                out.println("<tr>"
                    + "<td>"+rs.getInt("id")+"</td>"
                    + "<td>"+rs.getString("username")+"</td>"
                    + "<td>"+rs.getString("item_name")+"</td>"
                    + "<td>"+rs.getString("location")+"</td>"
                    + "<td><b style='color:"+(rs.getString("status").equals("Lost")?"#ef4444":"#10b981")+"'>"+rs.getString("status")+"</b></td>"
                    + "<td>"+rs.getString("contact")+"</td>"
                    + "<td>"+rs.getString("description")+"</td>"
                    + "<td><button onclick='openPopup("+rs.getInt("id")+")' style='width:auto; padding:5px 10px; font-size:12px;'>Resolve</button></td>"
                    + "</tr>");
            }
        } catch (Exception e) { e.printStackTrace(); }

        out.println("""
                    </table>
                </div>
                <br><a href="HomeServlet" style="color:#f59e0b; text-decoration:none;">← Back to Dashboard</a>
            </div>

            <div id="popup">
                <h3>Resolve Item</h3>
                <form action="ResolveItemServlet" method="post">
                    <input type="hidden" id="itemId" name="itemId">
                    <input type="text" name="name" placeholder="Your Name" required>
                    <input type="email" name="email" placeholder="Your Email" required>
                    <button type="submit">Mark as Resolved</button>
                    <button type="button" onclick="closePopup()" style="background:#475569">Cancel</button>
                </form>
            </div>
        </body>
        </html>
        """);
    }
}