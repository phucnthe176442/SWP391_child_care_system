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
import java.sql.Timestamp;
import models.Task;
import models.Testcase;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateTestcaseController", urlPatterns = {"/tests/create"})
public class CreateTestcaseController extends HttpServlet {

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String slug = (String) request.getParameter("slug").trim();
        TaskDBContext taskDBContext = new TaskDBContext();
        Task task = taskDBContext.getBySlug(slug);
        if (task != null) {
            TestcaseDBContext testcaseDBContext = new TestcaseDBContext();
            Testcase testcase = new Testcase();
            testcase.setSlug(slug);

            String input = (String) request.getParameter("input").trim();
            testcase.setInput(input);

            String output = (String) request.getParameter("output").trim();
            testcase.setOutput(output);

            if (!input.isEmpty() && !output.isEmpty()) {

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                testcase.setCreatedAt(timestamp);
                testcase.setUpdatedAt(timestamp);

                testcaseDBContext.insert(testcase);
            } else {
                request.setAttribute("error", "Input & Output can not be empty !");
            }
            
            testcaseDBContext = new TestcaseDBContext();
            
            request.setAttribute("task", task);
            request.setAttribute(
                    "testcases",
                    testcaseDBContext.listByTaskSlug(task.getSlug())
            );
            request.getRequestDispatcher(
                    "../views/createTest.jsp"
            ).forward(request, response);
            return;
        }
        response.sendRedirect("/homepage");
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
                create(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }

}
