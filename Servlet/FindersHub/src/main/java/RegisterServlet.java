import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setDob(request.getParameter("dob"));
        user.setAddress(request.getParameter("address"));
        user.setGender(request.getParameter("gender"));

        UserDAO dao = new UserDAO();
        int status = dao.register(user);

        if (status == 1) {
            out.print("<script>alert('Registration Successful');</script>");
            RequestDispatcher rd =
              request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
        else if (status == 2) {
            out.print("<script>alert('Email already exists');</script>");
            RequestDispatcher rd =
              request.getRequestDispatcher("register.html");
            rd.include(request, response);
        }
        else {
            out.print("<script>alert('Registration Failed');</script>");
            RequestDispatcher rd =
              request.getRequestDispatcher("register.html");
            rd.include(request, response);
        }
    }

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }
}
