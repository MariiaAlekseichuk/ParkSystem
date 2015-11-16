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
 * Created by ���� on 12.11.2015.
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
       // List usersList = new ArrayList();
       // List emaiList = new ArrayList();

    List <User> users=new ArrayList<User>();
        try {
            String query = "SELECT login,password,firstname,lastname,email,groupid FROM users ORDER BY firstname";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();



            while (resultSet.next()) {
                users.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getString(5)));
               /* int id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                System.out.println(id);
                System.out.println(email);
                usersList.add(id);
                emaiList.add(email);
                */
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {

        }
       // req.setAttribute("userList", usersList);
    req.setAttribute("users", users);

        try {
            req.getRequestDispatcher("users.jsp").forward(req, res);
        } catch (IOException e) {

        }

    }
}
