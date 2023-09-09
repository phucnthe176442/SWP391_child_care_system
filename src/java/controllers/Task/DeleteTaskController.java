/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.Task;

import dal.TaskDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Task;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteTaskController", urlPatterns = {"/tasks/delete"})
public class DeleteTaskController extends HttpServlet {

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt((String) request.getParameter("task_id"));
            TaskDBContext taskDBContext = new TaskDBContext();
            Task task = taskDBContext.get(id);
            if (task != null) {
                taskDBContext.delete(id);
            }
            response.sendRedirect("/homepage");
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
