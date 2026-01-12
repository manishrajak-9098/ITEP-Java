import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        boolean status = dao.login(email, password);

        if (status) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("HomeServlet");
        } else {
            out.print("<script>alert('Invalid Login');</script>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }
}
