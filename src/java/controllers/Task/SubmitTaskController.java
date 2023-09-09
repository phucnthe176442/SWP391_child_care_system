package controllers.Task;

import dal.SubmissionDBContext;
import dal.TaskDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import models.Submission;
import models.Task;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SubmitTaskController", urlPatterns = {"/tasks/submit"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class SubmitTaskController extends HttpServlet {

    protected void submit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        try {

            /* Receive file uploaded to the Servlet from the HTML5 form */
            Part filePart = request.getPart("solution");
            String fileName = filePart.getSubmittedFileName();
            String srcFilePath = "D:\\Workspace\\UniversityFPT\\Ky4\\PRJ301\\_Projects\\0.Assigment\\Assignment-PRJ301-CauPD\\build\\web\\public\\" + fileName;
            for (Part part : request.getParts()) {
                part.write(srcFilePath);
            }

            // init DBContexts
            UserDBContext userDBContext = new UserDBContext();
            SubmissionDBContext submissionDBContext = new SubmissionDBContext();
            TaskDBContext taskDBContext = new TaskDBContext();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            String slug = (String) request.getParameter("task_slug");
            Task task = taskDBContext.getBySlug(slug);

            Submission submission = new Submission();

            submission.setTaskname(task.getTaskname());
            submission.setUsername(user.getUsername());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            submission.setCreatedAt(timestamp);
            submission.setUpdatedAt(timestamp);

            submission.setSlug(slug);

            // get code
            StringBuilder code = new StringBuilder();
            try {
                File myObj = new File(srcFilePath);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    code.append(System.getProperty("line.separator"));
                    code.append(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            submission.setCode(code.toString());

            // get status
            submission.setStatus("Accepted");

            if (submission.getStatus().equals("Accepted")) {
                ArrayList<Submission> submissions = submissionDBContext.listByTaskSlugAndUsername(slug, user.getUsername());
                boolean isAddScore = true;
                for (Submission xSubmission : submissions) {
                    if (xSubmission.getStatus().equals("Accepted")) {
                        isAddScore = false;
                    }

                }

                if (isAddScore) {
                    user.setScore(user.getScore() + task.getScore());
                    user.setUpdatedAt(timestamp);
                    userDBContext.update(user);
                    session.setAttribute("user", user);
                }
            }

            submissionDBContext.insert(submission);

            response.sendRedirect("/homepage");
        } catch (Exception ex) {
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
        submit(request, response);
    }

}
