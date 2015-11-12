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
        // Connection connection=null;
        try {
            connection = getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List usersList = new ArrayList();
        try {
            String query = "SELECT id FROM users";
            PreparedStatement preparedStatment = null;
            preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println(id);
                usersList.add(id);
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {

        }
        req.setAttribute("userList", usersList);

        try {
            req.getRequestDispatcher("users.jsp").forward(req, res);
        } catch (IOException e) {

        }

    }
}
