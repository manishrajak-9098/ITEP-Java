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

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ItemDAO dao = new ItemDAO();
        ResultSet rs = dao.viewItems();

        out.println("""
        <html>
        <head>
        <title>Items</title>
        
         <link rel="stylesheet" href="style.css">

        <script>
        function openPopup(id){
            document.getElementById("itemId").value=id;
            document.getElementById("popup").style.display="block";
        }
        </script>
        </head>
        <body>

        <div class="card" style="width:95%">
        <h2>Lost & Found Items</h2>

        <table>
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Item</th>
                <th>Location</th>
                <th>Status</th>
                <th>Email</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        """);

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getInt("id")+"</td>");
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td>"+rs.getString("item_name")+"</td>");
                out.println("<td>"+rs.getString("location")+"</td>");
                out.println("<td>"+rs.getString("status")+"</td>");
                out.println("<td>"+rs.getString("email")+"</td>");
                out.println("<td>"+rs.getString("description")+"</td>");

                // 🔥 SAME BUTTON – bas popup kholega
                out.println("<td>");
                out.println(
                    "<button onclick='openPopup(" + rs.getInt("id") + ")'>" +
                    "Mark as Resolved" +
                    "</button>"
                );
                out.println("</td>");

                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("""
        </table>

        <!-- POPUP (hidden) -->
        <div id="popup" style="display:none;border:1px solid black;padding:10px;">
        <form action="ResolveItemServlet" method="post">
            <input type="hidden" id="itemId" name="itemId">
            <input type="text" name="name" placeholder="Your Name" required>
            <input type="email" name="email" placeholder="Your Email" required>
            <button type="submit">Submit</button>
        </form>
      
        </div>
        		   <a href="HomeServlet">Back to Dashboard</a>
        </div>
        </body>
        </html>
        """);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
