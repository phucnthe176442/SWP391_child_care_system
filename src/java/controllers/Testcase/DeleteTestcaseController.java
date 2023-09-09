package controllers.Testcase;

import dal.TaskDBContext;
import dal.TestcaseDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Task;
import models.Testcase;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteTestcaseController", urlPatterns = {"/tests/delete"})
public class DeleteTestcaseController extends HttpServlet {

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("testcase_id"));
        String slug = (String) request.getParameter("slug");
        TestcaseDBContext testcaseDBContext = new TestcaseDBContext();
        Testcase testcase = testcaseDBContext.get(id);
        if (testcase != null) {
            testcaseDBContext.delete(id);
            
            TaskDBContext taskDBContext = new TaskDBContext();
            Task task = taskDBContext.getBySlug(slug);
            
            request.setAttribute("task", task);
            request.setAttribute(
                    "testcases",
                    testcaseDBContext.listByTaskSlug(slug)
            );
            request.getRequestDispatcher(
                    "../views/createTest.jsp"
            ).forward(request, response);
            return;
        }

        response.sendRedirect("/homepage");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
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
