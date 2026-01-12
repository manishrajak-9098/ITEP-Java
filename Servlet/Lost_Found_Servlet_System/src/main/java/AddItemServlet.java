import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {

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

        Item item = new Item();
        item.setUsername(request.getParameter("username"));
        item.setEmail(request.getParameter("email"));
        item.setItemName(request.getParameter("itemName"));
        item.setLocation(request.getParameter("location"));
        item.setItemDate(request.getParameter("itemDate"));
        item.setContact(request.getParameter("contact"));
        item.setStatus(request.getParameter("status"));
        item.setDescription(request.getParameter("description"));

        ItemDAO dao = new ItemDAO();
        dao.addItem(item);

        out.print("<script>alert('Item Added Successfully');</script>");
        RequestDispatcher rd =
                request.getRequestDispatcher("HomeServlet");
        rd.include(request, response);
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
