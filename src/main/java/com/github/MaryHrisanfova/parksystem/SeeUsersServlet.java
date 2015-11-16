package com.github.MaryHrisanfova.parksystem;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.github.MaryHrisanfova.parksystem.User.*;

import static com.github.MaryHrisanfova.parksystem.DBConnection.getConnection;

/**
 * Created by Маша on 12.11.2015.
 */
@WebServlet(urlPatterns = "/users")
public class SeeUsersServlet extends HttpServlet {

    private Connection connection;
    // private DataSource datasource;
    private Statement statement;
    private ServletConfig config;

    public void init(ServletConfig config) throws ServletException {

    }

    /*
        private Connection getConnection() throws SQLException {
            return datasource.getConnection();
        }
    */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        try {
            connection = getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        List<User> users = new ArrayList<User>();
        UserDAO dao = new UserDAO();
        users = dao.getAllUser(users);

        req.setAttribute("users", users);

        try

        {
            req.getRequestDispatcher("users.jsp").forward(req, res);
        } catch (IOException e){

        }

    }
}
