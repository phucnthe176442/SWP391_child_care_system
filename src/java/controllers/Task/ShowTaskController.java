package controllers.Task;

import dal.TaskDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShowTaskController", urlPatterns = {"/tasks/show"})
public class ShowTaskController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String slug = (String) request.getParameter("slug");
            TaskDBContext taskDBContext = new TaskDBContext();
            Task task = taskDBContext.getBySlug(slug);
            request.setAttribute("task", task);
            request.getRequestDispatcher("../views/task.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("/homepage");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
