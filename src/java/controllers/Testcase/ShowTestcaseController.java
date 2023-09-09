/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.Task;

import dal.TaskDBContext;
import dal.TestcaseDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Task;
import models.Testcase;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShowTestcaseController", urlPatterns = {"/tests/show"})
public class ShowTestcaseController extends HttpServlet {

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            int id = Integer.parseInt((String) request.getParameter("task_id"));
            TaskDBContext taskDBContext = new TaskDBContext();
            Task task = taskDBContext.get(id);
            if (task != null) {
                
                TestcaseDBContext testcaseDBContext = new TestcaseDBContext();
                ArrayList<Testcase> testcases = testcaseDBContext.listByTaskSlug(
                        task.getSlug()
                );
                
                request.setAttribute("task", task);
                request.setAttribute("testcases", testcases);
                request.getRequestDispatcher(
                        "../views/createTest.jsp"
                ).forward(request, response);
                return;
            }
            response.sendRedirect("/homepage");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("/homepage");
        }
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
                index(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }

}
