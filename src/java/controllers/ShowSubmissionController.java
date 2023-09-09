package controllers;

import dal.SubmissionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Submission;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShowSubmissionController", urlPatterns = {"/submissions/show"})
public class ShowSubmissionController extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int id = Integer.parseInt((String) request.getParameter("submission_id"));
            SubmissionDBContext submissionDBContext = new SubmissionDBContext();
            Submission submission = submissionDBContext.get(id);
            request.setAttribute("code", submission.getCode());
            request.getRequestDispatcher("../views/submission.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("/");
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
