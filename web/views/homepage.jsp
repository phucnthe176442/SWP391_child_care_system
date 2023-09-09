<%-- 
    Document   : homepage
    Created on : Jun 13, 2023, 11:46:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import = "models.Submission"%>
<%@page import = "models.Task"%>
<%@page import = "java.text.DateFormat"%>  
<%@page import = "java.text.SimpleDateFormat"%>  
<%@page import = "java.util.ArrayList"%>
<%
    boolean isAdmin =  (boolean) request.getSession().getAttribute("isAdmin");
    ArrayList<Submission> submissions = (ArrayList<Submission>) request.getAttribute("submissions");
    ArrayList<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link rel="stylesheet" href="/views/css/index.css">
        <title>SWP391 Child Care System</title>
    </head>
    <body>
        <div class="container_new">
            <jsp:include page = "./common/header.jsp"/>

            <h1> This is homepage </h1>

            <jsp:include page = "./common/footer.jsp"/>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4025a1217e.js" crossorigin="anonymous"></script>
        <script src="./index.js"></script>
    </body>
</html>









