<%-- 
    Document   : footer
    Created on : Jun 14, 2023, 11:04:17 AM
    Author     : Admin
--%>

<%@page import = "models.User"%>
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<header class="header">    
    <form method="post" action="/homepage/users/updateName">
        <div class="popup_change_username">
            <div class="change_username">
                <p>Username Setting</p>
                <div>
                    <p id="old_username">Old Username:</p> <input type="text" name="old_username" required>
                    <div id="seperate"></div>
                </div>
                <div>
                    <p id="new_username">New Username:</p> <input type="text" name="new_username" required> 
                    <div id="seperate"></div>
                </div>

                <div class="btns"> 
                    <button id="cancel_username" type="button">Cancel</button>   
                    <button id="confirm" type="submit" name="typeFunction" value="2">Confirm</button>
                </div>
            </div>
        </div>
    </form>

    <form method="post" action="/users/update">
        <div class="popup_change_password">
            <div class="change_password">
                <p>Password Setting</p>
                <div>
                    <p id="old_password">Old Password:</p> <input type="password" name="old_password" required>
                    <div id="seperate"></div>
                </div>
                <div>
                    <p id="new_password">New Password:</p> <input type="password" name="new_password" required>
                    <div id="seperate"></div>
                </div>

                <div class="btns">
                    <button id="cancel_password" type="button">Cancel</button>
                    <button id="confirm" type="submit" name="typeFunction" value="1">Confirm</button>

                </div>
            </div>
        </div>
    </form>

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/homepage">
                <div class="nav_logo">
                    <img class="logo" src="/public/img/logo.png" alt="logo">
                    <div class="name-website">
                        SWP391 CCS Project
                    </div>
                </div>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/homepage">Home</a>
                    </li>
                </ul>
                <%
                    if(user != null) {
                %>
                <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">

                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <div id="username">
                                <%= user.getUsername() %>
                            </div>
                            <i id="user_symbol" class="fa-solid fa-user"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#" id="btn_change_password">Change password</a></li>
                            <li><a class="dropdown-item" href="#" id="btn_change_username">Change username</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <form action="/login" method="post">
                                    <input type="submit" class="dropdown-item" value="Logout">
                                    <input type="hidden" value="1" name="logout">
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
                <% } %>
            </div>
        </div>
    </nav>
</header>