package com.github.MaryHrisanfova.parksystem.dao;

import com.github.MaryHrisanfova.parksystem.model.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.github.MaryHrisanfova.parksystem.dao.DBConnection.getConnection;

/**
 * Created by Маша on 14.11.2015.
 */
public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            connection = getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public String getGroupID(String userGroup) {
        try {
            String query = "SELECT id FROM groups WHERE groups.groupname=?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, userGroup);
            preparedStatment.executeUpdate();
        } catch (SQLException e) {
        }
    }

    */


    /*
        public String setAttributeGroupID(int userGroupId) {
            String userGroup="";
            try {
                String query = "SELECT groupname FROM groups WHERE groups.id=?";
                PreparedStatement preparedStatment = connection.prepareStatement(query);
                preparedStatment.setInt(1, userGroupId);
                ResultSet rs = preparedStatment.executeQuery();
                userGroup=rs.getString(1);
            } catch (SQLException e) {
            }
            return userGroup;
        }
    */
    public void addUser(User user) {
        try {
            // String query = "INSERT INTO users (login,firstname,lastname,email,groupid) VALUES (?,?,?,?,?)";
            String query = "INSERT INTO users (login,password,firstname,lastname,email) VALUES (?,?,?,?,?)";
            // String query = "INSERT INTO users (login) VALUES (?)";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, user.getLogin());
            preparedStatment.setString(2, user.getPassword());
            preparedStatment.setString(3, user.getFirstname());
            preparedStatment.setString(4, user.getLasttname());
            preparedStatment.setString(5, user.getEmail());

            // preparedStatment.setString(5, user.getGroup());

            preparedStatment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserCorrect(String login, String password) {

        if (login == null || password == null)
            return false;

        String query = "select id from users where login = ? and password = ?";
        int result = 0;

        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, login);
            preparedStatment.setString(2, password);

            ResultSet resultSet = preparedStatment.executeQuery();
            if (resultSet.next())
                result = resultSet.getInt("id");
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllUser(List<User> users) {
        try {
            String query = "SELECT login,password,firstname,lastname,email,groupid FROM users ORDER BY firstname";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
        }
        return users;
    }



}
