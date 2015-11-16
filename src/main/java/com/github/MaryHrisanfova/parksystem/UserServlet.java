package com.github.MaryHrisanfova.parksystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Маша on 15.11.2015.
 */
@WebServlet (urlPatterns ="/addnewuser")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT = "/addNewUser.jsp";
    private UserDAO dao;

    //private static String UserRecord = "/addNewUser.jsp";
    public UserServlet() {
        super();
        dao = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "addNewUser.jsp";

        String action = new String();

        response.setContentType("text/html");
        action = request.getParameter("action");

        if (action.equals("insert")) {
            User user = new User();
            user.setPassword(request.getParameter("login"));
            user.setLogin(request.getParameter("password"));
            user.setFirstname(request.getParameter("firstName"));
            user.setLasttname(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));

            dao.addUser(user);
            request.getRequestDispatcher(redirect).forward(request, response);
        }

    }
}
