/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.User;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/users/update"})
public class UpdatePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/homepage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password").trim();
            if (user != null) {
                boolean isValid = user.getPassword().equals(oldPassword);
                if (isValid) {
                    user.setPassword(newPassword);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    user.setUpdatedAt(timestamp);
                    UserDBContext userDBContext = new UserDBContext();
                    userDBContext.update(user);
                }
                response.sendRedirect("/homepage");
                return;
            }
            response.sendRedirect("/");
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }

}
