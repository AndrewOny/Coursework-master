User
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Read existing Task</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <h4>Task details</h4>
        <div class="container task-details">
            <p>Task id:<b> <c:out value="${task.getId()}"/></b></p>
            <p>Task title:<b> <c:out value="${task.getTitle()}"/></b></p>
            <p>Task priority:<b> <c:out value="${task.getPriority()}"/></b></p>
            <a class="btn btn-read"
               href="${pageContext.request.contextPath}/edit-task?id=${task.getId()}">> Edit task</a>
        </div>
    </div>
</main>
</body>
</html>
<%@include file="footer.html" %>