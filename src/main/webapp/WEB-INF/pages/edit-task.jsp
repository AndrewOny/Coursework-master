<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit existing Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <form class="container task-form" action="${pageContext.request.contextPath}/edit-task" method="post">
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" id="id" name="id" value="${task.getId()}" readonly>
            </div>
            <div class="form-group">
                <label for="title">Name</label>
                <input type="text" id="title" name="title" placeholder="Enter task name" value="${task.getTitle()}">
            </div>
            <div class="form-group">
                <label for="priority">Priority</label>
                <select class="priority-select" id="priority" name="priority">
                    <c:forEach var="priority" items="${priorities}">
                        <option value="${priority}" ${priority == task.getPriority() ? 'selected' : ''}>${priority}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input class="btn btn-read" type="submit" value="Update">
                <input class="btn btn-edit" type="reset" value="Clear">
            </div>
        </form>
        <c:if test="${not empty message}">
            <p class="message err_message">${message}</p>
        </c:if>
    </div>
</main>
</body>
</html>
<%@include file="footer.html" %>
