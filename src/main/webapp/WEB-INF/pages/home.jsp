<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <img src="img/to-do-list.png" alt="Logo">
        <h2>Login to awesome To-Do List</h2>
        <form action="create-task" method="get">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password">
            </div>
            <div>
                <input type="submit" value="Login">
            </div>
        </form>
    </div>
</main>
<%@include file="footer.html" %>
</body>
</html>


