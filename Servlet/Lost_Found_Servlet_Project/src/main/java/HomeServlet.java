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
            "<link rel='stylesheet' href='style.css'>" +
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
