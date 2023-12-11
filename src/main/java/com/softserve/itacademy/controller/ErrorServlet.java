package com.softserve.itacademy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/taskError")
public class ErrorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = ((Throwable) request.getAttribute("javax.servlet.error.exception")).getMessage();
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        request.setAttribute("message", message == null ? "Unknown error" : message);
        request.setAttribute("url", requestUri == null ? "Unknown URI" : requestUri);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        requestDispatcher.forward(request, response);
    }
}
