<%-- 
    Document   : ranking
    Created on : Jun 14, 2023, 10:04:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import = "models.User"%>
<%@page import = "java.util.ArrayList"%>
<%
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
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

            <div class="ranking">

                <div class="ranking_table" style="overflow-x: auto;">
                    <table>
                        <tr class="ranking_categories">
                            <th class="rank_rank">#</th>
                            <th class="username_rank">Name</th>
                            <th class="score">Score</th>
                        </tr>
                        <%
                            int rank = 1;
                            for(User user: users) { 
                                if(user.getUsername().equals("admin")) continue;
                        %>
                        <tr class="ranking_content">
                            <td class="rank_rank"><%= rank %></td>
                            <td class="username_rank"><%= user.getUsername() %></td>
                            <td class="score"><%= user.getScore() %></td>
                        </tr>
                        <% 
                            rank++;
                            } 
                        %>  
                    </table>
                </div>
                <ul class="ranking_end">
                </ul>
            </div>

            <jsp:include page = "./common/footer.jsp"/>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4025a1217e.js" crossorigin="anonymous"></script>
        <script src="./index.js"></script>
    </body>
</html>




