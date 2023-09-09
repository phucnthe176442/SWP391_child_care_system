<%-- 
    Document   : loginFail
    Created on : Jun 13, 2023, 11:45:36 PM
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
            <div class="container-new">
                <div class="Right_side">
                    <div class="login-box">
                        <h2>Login</h2>
                        <form method="POST" action="/login">
                            <div class="user-box">
                                <input type="text" name="username" required="" />
                                <label>Username</label>
                            </div>
                            <div class="user-box">
                                <input type="password" name="password" required="" />
                                <label>Password</label>
                            </div>
                            <div>
                                Wrong username or password!
                            </div>
                            <br><br><br>
                            <div class="remember-me--forget-password">
                                <label>
                                    <input type="checkbox" name="item" checked />
                                    <span class="text-checkbox">Remember me</span>
                                </label>
                            </div>
                            <button type="submit">
                                Login
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4025a1217e.js" crossorigin="anonymous"></script>
        <script src="./index.js"></script>
    </body>
</html>


