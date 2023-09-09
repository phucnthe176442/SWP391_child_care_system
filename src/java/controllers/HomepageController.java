package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dal.SubmissionDBContext;
import dal.TaskDBContext;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomepageController", urlPatterns = {"/homepage"})
public class HomepageController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            boolean isAdmin = (boolean) session.getAttribute("isAdmin");
            User user = (User) session.getAttribute("user");

            TaskDBContext taskDBContext = new TaskDBContext();
            request.setAttribute("tasks", taskDBContext.list());
            SubmissionDBContext submissionDBContext = new SubmissionDBContext();
            if (isAdmin) {
                request.setAttribute(
                        "submissions",
                        submissionDBContext.list()
                );
            } else {
                request.setAttribute(
                        "submissions",
                        submissionDBContext.listByUsername(user.getUsername())
                );
            }

            request.getRequestDispatcher("views/homepage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
    }
}
