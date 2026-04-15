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
            <link rel="stylesheet" href="">
            <style>
        * { margin:0; padding:0; box-sizing:border-box; font-family: 'Segoe UI', sans-serif; }

body{
    min-height:100vh;
    background: linear-gradient(180deg, #312e81, #0f172a);
    display:flex;
    align-items:center;
    justify-content:center;
    color:#e0e7ff;
}


.card {
    width:100%;
    max-width:400px;
    padding:30px;
    background: #020617;
    border-radius:14px;
    box-shadow: 0 15px 40px rgba(0,0,0,0.8);
    text-align:center;
    border-top:4px solid #f59e0b;
}

/* Jab table dikhani ho tab card bada ho jaye */
.wide-card { max-width: 1100px; }

h2 { margin-bottom:10px; color:#f8fafc; }
.sub { color:#94a3b8; margin-bottom:20px; font-size:14px; }

input, select, textarea {
    width:100%; padding:12px; margin:8px 0;
    border-radius:8px; border:1px solid #1e293b;
    background:#020617; color:#f8fafc; outline:none;
}

input:focus { border-color:#f59e0b; }

button {
    width:100%; padding:12px; background:#f59e0b;
    color:#020617; border:none; border-radius:8px;
    font-weight:600; cursor:pointer; margin-top:10px;
}

button:hover { background:#fbbf24; }

/* Table Styling */
.table-wrapper { overflow-x: auto; margin-top: 20px; }
table { width:100%; border-collapse:collapse; min-width: 800px; }
th, td { padding:12px; border-bottom:1px solid #1e293b; text-align:left; font-size: 14px; }
th { color:#f59e0b; }

/* Popup */
#popup {
    display:none; position:fixed; top:50%; left:50%;
    transform:translate(-50%, -50%); background:#0f172a;
    padding:30px; border-radius:15px; border:2px solid #f59e0b;
    z-index:100; width:350px;
}
#overlay {
    display:none; position:fixed; top:0; left:0;
    width:100%; height:100%; background:rgba(0,0,0,0.8); z-index:99;
}

</style>
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
              
        			(()=>{
        					window.history.forward();
        				})();

            </script>
        </head>
        <body>
            <div id="overlay" onclick="closePopup()"></div>
            <div class="card wide-card">
                <h2>FindersHub</h2>
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