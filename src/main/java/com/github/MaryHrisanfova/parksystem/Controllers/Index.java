package com.github.MaryHrisanfova.parksystem.controllers;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 19.11.2015.
 */

@WebServlet(urlPatterns = "/index")
public class Index extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        request.getRequestDispatcher("index.jsp").forward(request, response);
}
}