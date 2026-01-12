import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Item item = new Item();
        item.setUsername(request.getParameter("username"));
        item.setEmail(request.getParameter("email"));
        item.setItemName(request.getParameter("itemName"));
        item.setLocation(request.getParameter("location"));
        item.setContact(request.getParameter("contact"));
        item.setDescription(request.getParameter("description"));
        item.setStatus(request.getParameter("status"));
        item.setItemDate(request.getParameter("itemDate"));

        ItemDAO dao = new ItemDAO();
        boolean success = dao.addItem(item);

        out.println("<html><body>");
        if (success) {
            out.println("<script>alert('Item Posted Successfully!'); window.location='HomeServlet';</script>");
        } else {
            out.println("<script>alert('Error posting item. Try again.'); window.location='addItem.html';</script>");
        }
        out.println("</body></html>");
    }
}