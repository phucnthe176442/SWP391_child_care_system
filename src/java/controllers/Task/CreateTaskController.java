package controllers.Task;

import dal.TaskDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import models.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateTaskController", urlPatterns = {"/tasks/create"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class CreateTaskController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boolean isAdmin = (boolean) session.getAttribute("isAdmin");
            if (isAdmin) {
                index(request, response);
            } else {
                response.sendRedirect("/homepage");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("/");
        }
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

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../views/createTask.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("task_description");
        String fileName = filePart.getSubmittedFileName();
        String srcFilePath = "D:\\Workspace\\UniversityFPT\\Ky4\\PRJ301\\_Projects\\0.Assigment\\Assignment-PRJ301-CauPD\\build\\web\\public\\tasks\\" + fileName;
        for (Part part : request.getParts()) {
            part.write(srcFilePath);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String slug = timestamp.toString();
        slug = slug.replace(" ", "");
        slug = slug.replace(":", "");
        slug = slug.replace(".", "");
        slug = slug.replace("-", "");
        fileName = fileName.split(".pdf")[0];
        slug = fileName + slug;
        fileName = slug;
        fileName = fileName + ".pdf";
        // change file name
        try {
            Path source = Paths.get(srcFilePath);
            Files.move(source, source.resolveSibling(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Task task = new Task();
        task.setTaskname((String) request.getParameter("task_name"));
        task.setTaskDescription(task.getTaskname());
        task.setScore(Integer.parseInt((String) request.getParameter("score")));
        TaskDBContext taskDBContext = new TaskDBContext();
        task.setCreatedAt(timestamp);
        task.setUpdatedAt(timestamp);
        task.setSlug(slug);
        task.setTimeLimit("1000");
        task.setMemoryLimit("256");
        taskDBContext.insert(task);
        response.sendRedirect("/homepage");
    }

}
