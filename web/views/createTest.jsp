<%-- 
    Document   : createTest
    Created on : Jun 25, 2023, 10:54:29 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import = "models.Testcase" %>
<%@page import = "models.Task" %>
<%@page import = "java.util.ArrayList"%>
<% 
  Task task = (Task) request.getAttribute("task"); 
  if(task == null)
    task = (Task) request.getSession().getAttribute("task"); 
  ArrayList<Testcase> testcases = (ArrayList<Testcase>) request.getAttribute("testcases");
  if(testcases == null) 
    testcases = (ArrayList<Testcase>) request.getSession().getAttribute("testcases");
  String error = (String) request.getAttribute("error");
%>
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

            <h1>
                Add test for <%= task.getTaskname() %>
            </h1>
            <form method="POST" action="/tests/create">
                <div class="mb-3">
                    <label for="input" class="form-label">Input</label>
                    <textarea 
                        type="text" 
                        name="input" 
                        class="form-control" 
                        id="input" 
                        required>
                    </textarea>
                </div>
                <div class="mb-3">
                    <label for="output" class="form-label">Output</label>
                    <textarea 
                        type="text" 
                        name="output" 
                        class="form-control" 
                        id="output" 
                        required/>
                    </textarea>
                </div>
                <input type="hidden" name="slug" value="<%= task.getSlug() %>">
                <button type="submit">Add Test</button>
            </form>

            <div class="error mt-4 bg-white mb-4">
                <% if(error != null && !error.isEmpty()) {%>
                <%= error %>
                <% } %>
            </div>

            <button class="mt-4 bg-white mb-4 bbacktohome">
                <a href="/homepage" class="text-dark">Back to home</a>
            </button>

            <ul class="list-group list-group-horizontal-md test_list_all">
                <%
                  int id = 1;
                  for(Testcase testcase : testcases) {
                %>
                <li 
                    class="list-group-item mr-4 test_list" 
                    title='<%= testcase.getInput() + "\n" %> => <%= "\n" + testcase.getOutput() %>'
                    >

                    <form method="post" action="/tests/delete">
                        <input type="hidden" name="testcase_id" value="<%= testcase.getId() %>">
                        <input type="hidden" name="slug" value="<%= testcase.getSlug() %>">
                        <button 
                            onclick="return confirm('Are you sure?')" 
                            type="submit"
                            >Test <%= id %><i class="fa-solid fa-circle-minus"></i>
                        </button>
                    </form>

                </li>
                <% 
                    id++;
                    } 
                %>
            </ul>

            <div style="height: 6em"></div>

            <jsp:include page = "./common/footer.jsp"/>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4025a1217e.js" crossorigin="anonymous"></script>
        <script src="./index.js"></script>
    </body>
</html>

