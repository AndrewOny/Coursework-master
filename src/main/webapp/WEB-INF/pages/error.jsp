<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <title>Error Page</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <img src="img/no-results.png" alt="Logo">
        <h4>NOT FOUND</h4>
        <p class="message err_message">"${message}"</p>
        <a class="btn btn-read"
           href="${pageContext.request.contextPath}/tasks-list">> Goto all tasks</a>
    </div>
</main>
</body>
</html>
<main>
</main>
<%@include file="footer.html" %>