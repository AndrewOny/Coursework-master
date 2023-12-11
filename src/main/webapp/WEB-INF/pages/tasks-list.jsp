<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tasks" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Tasks</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<main>
    <div class="content">
        <c:choose>
            <c:when test="${empty tasks}">

                <h4>To-do list is empty.</h4>
                <p>To add new task please click</p>
                <a class="btn btn-read"
                   href="${pageContext.request.contextPath}/create-task">+ Add new task</a>

            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Priority</th>
                        <th colspan="3">Operations</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.getId()}</td>
                            <td>${task.getTitle()}</td>
                            <td>${task.getPriority()}</td>
                            <td class="action_cell">
                                <a class="btn btn-read"
                                   href="${pageContext.request.contextPath}/read-task?id=${task.getId()}">Read</a>
                            </td>
                            <td class="action_cell">
                                <a class="btn btn-edit"
                                   href="${pageContext.request.contextPath}/edit-task?id=${task.getId()}">Edit</a>
                            </td>
                            <td class="action_cell">
                                <a class="btn btn-delete"
                                   href="${pageContext.request.contextPath}/delete-task?id=${task.getId()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<%@include file="footer.html" %>


