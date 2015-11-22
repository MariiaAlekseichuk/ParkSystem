package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.UserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Сервлет устанавливает выбранный пользователем язык в качестве параметра сессии,
 * записывает в параметры сессии логин и роль (groupid) в случае удачной авторизации,
 * затем перенаправляет на запрашиваему страницу /tasks
 *
 * @author Маша
 * @since 19.11.2015.
 */

@WebServlet(urlPatterns = "/index")
public class Index extends HttpServlet {
    private UserDAO dao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        dao = new UserDAO();

        if (dao.isUserCorrect(request.getParameter("login"), request.getParameter("password"))) {
            request.getSession().setAttribute("login", request.getParameter("login"));
            request.getSession().setAttribute("groupid", dao.getGroupId(request.getParameter("login")));
            response.sendRedirect("/tasks");
        } else
            context.getRequestDispatcher("/error.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
