package controllers.User;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteUserController", urlPatterns = {"/users/delete"})
public class DeleteUserController extends HttpServlet {

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt((String) request.getParameter("user_id"));
            UserDBContext userDBContext = new UserDBContext();
            User user = userDBContext.get(id);
            if (user != null && !user.getUsername().equals("admin")) {
                userDBContext.delete(id);
            }
            response.sendRedirect("/users/show");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("/homepage");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boolean isAdmin = (boolean) session.getAttribute("isAdmin");
            if (isAdmin) {
                delete(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }

}
