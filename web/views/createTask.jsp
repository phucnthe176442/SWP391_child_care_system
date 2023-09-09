<%-- 
    Document   : createTask
    Created on : Jun 25, 2023, 10:56:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

            <form method="POST" action="/tasks/create" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="task_name" class="form-label">Task Name</label>
                    <input type="text" name="task_name" class="form-control" id="task_name" placeholder="Enter task name" required />
                </div>
                <div class="mb-3">
                    <label for="score" class="form-label">Score</label>
                    <input type="number" name="score" class="form-control" id="score" placeholder="Enter score" required />
                </div>
                <div class="mb-3">
                    <label for="task_description" class="form-label">Task Description</label>
                    <input type="file" name="task_description" class="form-control" id="task_description"
                           placeholder="Upload task description" required accept="application/pdf" />
                </div>
                <button type="submit">Add Task</button>
            </form>

            <script>
                var uploadField = document.getElementById("task_description");
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
                    if (upld != 'pdf') {
                        alert("Only PDF are allowed");
                        this.value = "";
                        ;
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

