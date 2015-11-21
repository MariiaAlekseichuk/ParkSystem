package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.model.User;
import com.github.MaryHrisanfova.parksystem.dao.UserDAO;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.github.MaryHrisanfova.parksystem.dao.DBConnection.getConnection;

/**
 * Created by Маша on 12.11.2015.
 */
@WebServlet(urlPatterns = "/users")
public class SeeUsersServlet extends HttpServlet {

    private Connection connection;
    private Statement statement;
    private ServletConfig config;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute("login");
        Integer groupid = (Integer)session.getAttribute("groupid");

        if (groupid!=1){
            try {
                context.getRequestDispatcher("/error.jsp").forward(request, response);
            }
            catch (IOException e){

            }
        }

        else {
            try {
                connection = getConnection();

            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.setContentType("text/html");

            List<User> users = new ArrayList<User>();
            UserDAO dao = new UserDAO();
            users = dao.getAllUser(users);

            request.setAttribute("users", users);

            try

            {
                context.getRequestDispatcher("/users.jsp").forward(request, response);
            } catch (IOException e) {

            }
        }

    }
}
