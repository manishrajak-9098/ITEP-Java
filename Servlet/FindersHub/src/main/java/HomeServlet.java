import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        // 🔐 Login check
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String email = session.getAttribute("email").toString();
        String name = email.contains("@") ? email.split("@")[0] : "User";
        name = name.substring(0,1).toUpperCase() + name.substring(1);

        out.println(
            "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<title>Dashboard</title>" +
            "<link rel='stylesheet' href=''>"
            + "<script>\r\n"
            + "	(()=>{\r\n"
            + "		window.history.forward();\r\n"
            + "	})();\r\n"
            + "</script>"
            + "<style>"
            + "* { margin:0; padding:0; box-sizing:border-box; font-family: 'Segoe UI', sans-serif; }\r\n"
            + "\r\n"
            + "body{\r\n"
            + "    min-height:100vh;\r\n"
            + "    background: linear-gradient(180deg, #312e81, #0f172a);\r\n"
            + "    display:flex;\r\n"
            + "    align-items:center;\r\n"
            + "    justify-content:center;\r\n"
            + "    color:#e0e7ff;\r\n"
            + "}\r\n"
            + "\r\n"
            + "\r\n"
            + ".card {\r\n"
            + "    width:100%;\r\n"
            + "    max-width:400px;\r\n"
            + "    padding:30px;\r\n"
            + "    background: #020617;\r\n"
            + "    border-radius:14px;\r\n"
            + "    box-shadow: 0 15px 40px rgba(0,0,0,0.8);\r\n"
            + "    text-align:center;\r\n"
            + "    border-top:4px solid #f59e0b;\r\n"
            + "}\r\n"
            + "\r\n"
            + "/* Jab table dikhani ho tab card bada ho jaye */\r\n"
            + ".wide-card { max-width: 1100px; }\r\n"
            + "\r\n"
            + "h2 { margin-bottom:10px; color:#f8fafc; }\r\n"
            + ".sub { color:#94a3b8; margin-bottom:20px; font-size:14px; }\r\n"
            + "\r\n"
            + "input, select, textarea {\r\n"
            + "    width:100%; padding:12px; margin:8px 0;\r\n"
            + "    border-radius:8px; border:1px solid #1e293b;\r\n"
            + "    background:#020617; color:#f8fafc; outline:none;\r\n"
            + "}\r\n"
            + "\r\n"
            + "input:focus { border-color:#f59e0b; }\r\n"
            + "\r\n"
            + "button {\r\n"
            + "    width:100%; padding:12px; background:#f59e0b;\r\n"
            + "    color:#020617; border:none; border-radius:8px;\r\n"
            + "    font-weight:600; cursor:pointer; margin-top:10px;\r\n"
            + "}\r\n"
            + "\r\n"
            + "button:hover { background:#fbbf24; }\r\n"
            + "\r\n"
            + "/* Table Styling */\r\n"
            + ".table-wrapper { overflow-x: auto; margin-top: 20px; }\r\n"
            + "table { width:100%; border-collapse:collapse; min-width: 800px; }\r\n"
            + "th, td { padding:12px; border-bottom:1px solid #1e293b; text-align:left; font-size: 14px; }\r\n"
            + "th { color:#f59e0b; }\r\n"
            + "\r\n"
            + "/* Popup */\r\n"
            + "#popup {\r\n"
            + "    display:none; position:fixed; top:50%; left:50%;\r\n"
            + "    transform:translate(-50%, -50%); background:#0f172a;\r\n"
            + "    padding:30px; border-radius:15px; border:2px solid #f59e0b;\r\n"
            + "    z-index:100; width:350px;\r\n"
            + "}\r\n"
            + "#overlay {\r\n"
            + "    display:none; position:fixed; top:0; left:0;\r\n"
            + "    width:100%; height:100%; background:rgba(0,0,0,0.8); z-index:99;\r\n"
            + "}"
            + "</style>" +
            "</head>" +

            "<body>" +

            "<div class='card'>" +

            "<h2>Welcome, " + name + "</h2>" +
            "<p class='sub'>" + email + "</p>" +

            "<a href='addItem.html'>" +
            "<button>Add Item</button>" +
            "</a>" +

            "<a href='ViewItemServlet'>" +
            "<button style='margin-top:10px'>View Items</button>" +
            "</a>" +

            "<a href='LogoutServlet'>" +
            "<button style='margin-top:10px;background:#ef4444;color:white'>" +
            "Logout</button>" +
            "</a>" +

            "</div>" +

            "</body>" +
            "</html>"
        );
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
