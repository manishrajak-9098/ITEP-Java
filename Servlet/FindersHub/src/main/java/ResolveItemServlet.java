import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResolveItemServlet")
public class ResolveItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("itemId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        new ItemDAO().resolveItem(id, name, email);

        response.sendRedirect("ViewItemServlet");
    }
}
