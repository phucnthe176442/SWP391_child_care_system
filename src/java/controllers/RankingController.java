package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.UserDBContext;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RankingController", urlPatterns = {"/ranking"})
public class RankingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        index(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/");
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.listByRank();
        request.setAttribute("users", users);
        request.getRequestDispatcher("views/ranking.jsp").forward(request, response);
    }

}
