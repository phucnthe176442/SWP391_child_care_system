package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dal.UserDBContext;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
            response.sendRedirect("/login");
            return;
        }
        if (isLoginValid(request)) {
            response.sendRedirect("/homepage");
            return;
        }

        request.getRequestDispatcher("views/loginFail.jsp").forward(request, response);
    }

    protected boolean isLoginValid(HttpServletRequest request)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBContext userDBContext = new UserDBContext();
        User xUser = userDBContext.getByUsername(username);
        if (xUser == null) {
            return false;
        }

        boolean isLoginValid = xUser.getPassword().equals(password);
        if (!isLoginValid) {
            return false;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", xUser);
        boolean isAdmin = false;
        if (username.equals("admin")) {
            isAdmin = true;
        }
        session.setAttribute("isAdmin", isAdmin);
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
