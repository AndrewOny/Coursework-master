package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
//import org.springframework.http.HttpHeaders;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet("/create-task")
public class CreateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("priorities", Priority.values());
        request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        String title = request.getParameter("title");
        Priority priority = Priority.valueOf(request.getParameter("priority").toUpperCase());
        Task newTask = new Task(title, priority);

        if (taskRepository.create(newTask)) {
            response.sendRedirect("/tasks-list");
        } else {
            request.setAttribute("priorities", Priority.values());
            request.setAttribute("task", newTask);
            request.setAttribute("isExists", true);
            request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp").forward(request, response);
        }
    }
}