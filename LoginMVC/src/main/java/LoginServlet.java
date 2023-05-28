import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Login login = new Login(username, password);
        LoginDAO loginDAO = new LoginDAO();
        
        if (loginDAO.validate(login)) {
            // Login successful
            response.sendRedirect("success.jsp");
        } else {
            // Login failed
            response.sendRedirect("error.jsp");
        }
    }
}
