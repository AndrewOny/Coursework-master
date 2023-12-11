<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <form class="container task-form" action="${pageContext.request.contextPath}/create-task" method="post">
            <div class="form-group">
                <label for="title">Task</label>
                <textarea class="<c:if test="${not empty errorMessage}">err</c:if>"
                          id="title" name="title"
                          placeholder="Enter task name">${title}</textarea>
            </div>
            <div class="form-group">
                <label for="priority">Priority</label>
                <select class="priority-select" id="priority" name="priority">
                    <c:forEach var="priority" items="${priorities}">
                        <option value="${priority}" ${priority == selectedPriority ? 'selected' : ''}>${priority}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input class="btn btn-read" type="submit" value="Create">
                <input class="btn btn-edit" type="reset" value="Clear">
            </div>
        </form>
        <c:if test="${isExists}">
            <p class="message err_message">Task with a given name already exists!</p>
        </c:if>
        <c:if test="${param.success == 'true'}">
            <p class="message suc_message">Task "${param.title}" added successfully!</p>
        </c:if>
    </div>
</main>
<%@include file="footer.html" %>