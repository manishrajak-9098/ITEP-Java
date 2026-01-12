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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }

        out.println("""
            <html>
            <head>
                <title>Dashboard</title>
                <link rel="stylesheet" href="style.css">
            </head>
            <body>
                <div class="card">
                    <h2>Dashboard</h2>
                    <p class="sub">Welcome, %s</p>
                    <div class="menu">
                        <a href="addItem.html">Add Item</a>
                        <a href="ViewItemServlet">View Items</a>
                        <a href="LogoutServlet">Logout</a>
                    </div>
                </div>
            </body>
            </html>
        """.formatted(session.getAttribute("email")));
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
