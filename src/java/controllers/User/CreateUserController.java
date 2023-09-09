package controllers.User;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import dal.TaskDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/users/create"})
public class CreateUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boolean isAdmin = (boolean) session.getAttribute("isAdmin");
            if (isAdmin) {
                index(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boolean isAdmin = (boolean) session.getAttribute("isAdmin");
            if (isAdmin) {
                create(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../views/createUser.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        User user = new User();
        String username = request.getParameter("user_name").trim();
        String email = request.getParameter("email").trim();
        user.setUsername(username);
        user.setEmail(email);
        boolean isValid = true;
        ArrayList<User> users = userDBContext.list();
        for(User xUser : users) {
            if(xUser.getUsername().equals(username))
                isValid = false;
            if(xUser.getEmail().equals(email))
                isValid = false;
        }
        if(isValid) {
            user.setPassword("1");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            user.setCreatedAt(timestamp);
            user.setUpdatedAt(timestamp);
            user.setScore(0);
            userDBContext.insert(user);
            response.sendRedirect("/users/show");
        } else 
            request.getRequestDispatcher("../views/createUser.jsp").forward(request, response);
    }

}
