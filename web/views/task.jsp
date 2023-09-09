<%-- 
    Document   : task.jsp
    Created on : Jun 25, 2023, 12:53:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import = "models.Task" %>
<% Task task = (Task) request.getAttribute("task"); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link rel="stylesheet" href="/views/css/index.css">
        <title>Online Judge PRJ301</title>
    </head>
    <body>
        <div class="container_new">
            <jsp:include page = "./common/header.jsp"/>

            <div class="container_task_and_submit">
                <div class="problem_content">
                    <div class="tool_bar">
                        <p><%= task.getTaskname() %></p>
                    </div>
                    <iframe src="/public/tasks/<%= task.getSlug() %>.pdf"></iframe>
                </div>

                <div class="submit">
                    <form method="post" action="/tasks/submit" enctype="multipart/form-data">
                        <input type="hidden" value="<%= task.getSlug() %>" name="task_slug">
                        <button type="submit">Submit</button><br>
                        <input type="file" id="solution" name="solution" required accept=".c"><br>
                    </form>
                </div>
            </div>

            <div style="height: 6em;background-color: #f6f6f6;">
            </div>
            <script>
                var uploadField = document.getElementById("solution");
                var myFile = "";
                uploadField.onchange = function () {
                    if (this.files[0].size > 2 * 1024 * 1024) {
                        alert("File is too big!");
                        this.value = "";
                    }
                    ;
                    myFile = this.files[0].name;
                    console.log(myFile);
                    var upld = myFile.split('.').pop();
                    if (upld != 'c') {
                        alert("Only C file are allowed");
                        this.value = "";
                    }
                };
            </script>

            <jsp:include page = "./common/footer.jsp"/>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4025a1217e.js" crossorigin="anonymous"></script>
        <script src="./index.js"></script>
    </body>
</html>



