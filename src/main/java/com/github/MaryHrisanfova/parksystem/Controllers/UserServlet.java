package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.UserDAO;
import com.github.MaryHrisanfova.parksystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Регистрация нового пользователя
 * @author Маша
 * @since 15.11.2015.
 */
@WebServlet(urlPatterns = "/addnewuser")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT = "/addNewUser.jsp";
    private UserDAO dao;

    /**
     * Конструктор создает объект класса UserDAO для отправки запросов в БД
     * @see UserDAO
     */
    public UserServlet() {
        super();
        dao = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recordadded", "");
        ServletContext context = getServletContext();

        request.setCharacterEncoding("utf-8");//Для отправки русских букв
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            doPost(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("addNewUser.jsp");
            rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "addNewUser.jsp";

        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");

        User user = new User();
        user.setPassword(request.getParameter("password"));
        user.setLogin(request.getParameter("login"));
        user.setFirstname(request.getParameter("firstName"));
        user.setLasttname(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));

        dao.addUser(user);
        request.setAttribute("recordadded", "✅");
        request.getRequestDispatcher(redirect).forward(request, response);
    }


}
