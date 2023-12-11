package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        setAttributes(request, task, "");

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Task task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        if (task == null) task = new Task(); // stub for test

        response.setContentType("text/html;charset=UTF-8");
        task.setTitle(request.getParameter("title"));
        task.setPriority(Priority.valueOf(request.getParameter("priority").toUpperCase()));

        if (taskRepository.update(task)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.sendRedirect("/tasks-list");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            setAttributes(request, task, "Task with a given name already exists!");
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        }
    }

    private void setAttributes(HttpServletRequest request, Task task, String message) {
        request.setAttribute("priorities", Priority.values());
        request.setAttribute("task", task);
        request.setAttribute("message", message);
    }
}
