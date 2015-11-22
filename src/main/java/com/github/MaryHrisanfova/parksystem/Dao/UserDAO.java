package com.github.MaryHrisanfova.parksystem.dao;

import com.github.MaryHrisanfova.parksystem.model.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.github.MaryHrisanfova.parksystem.dao.DBConnection.getConnection;
import org.apache.log4j.Logger;
/**Класс для извлечения информации о пользователях,
 * содержащейся в таблицах park.users и park.task
 * Все методы класса содержат соответсвующий SQL-запрос
 * @exception SQLException во время выполнения запросов.
 * @see SQLException
 * @author Маша
 * @since 14.11.2015.
 */
public class UserDAO {
    final static Logger logger = Logger.getLogger(DBConnection.class);
    private Connection connection;

    /**Конструктор создает подключение к пулу соединений
     */
    public UserDAO() {
            connection = getConnection();
    }


    public void addUser(User user) {

        String query = "INSERT INTO users (login,password,firstname,lastname,email) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatment = connection.prepareStatement(query);

            preparedStatment.setString(1, user.getLogin());
            preparedStatment.setString(2, user.getPassword());
            preparedStatment.setString(3, user.getFirstname());
            preparedStatment.setString(4, user.getLasttname());
            preparedStatment.setString(5, user.getEmail());

            preparedStatment.executeUpdate();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }

   /** Сравнивает введенные пользователем в форме входа данные (логин, пароль)
    * с данными в БД - таблице users.
    */
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
            logger.error("Не удалось выполнить SQL-запрос" + query);
            throw new RuntimeException(e);
        }
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllUser(List<User> users) {

        String query = "SELECT login,password,firstname,lastname,email,groupid FROM users ORDER BY lastname";

        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return users;
    }





    public void getUsersFLnamesAndID(List<User> users) {

        String query = "SELECT id,lastname,firstname FROM users ORDER BY lastname";

        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
               users.add(new User(resultSet.getInt(1),resultSet.getString("lastname"),resultSet.getString("firstname")));
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }


    public User getUserById(int id) {
        User user=new User();
        String query = "SELECT lastname,firstname from users WHERE id=?";
        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
                user.setLasttname(resultSet.getString("lastname"));
                user.setFirstname(resultSet.getString("firstname"));
            }

            if (id == 0) {

                SQLException e;
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }

        return user;
    }



    public User getUserByLogin(String login) {
        User user=new User();
        String query = "SELECT id,lastname,firstname from users WHERE login=?";
        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, login);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLasttname(resultSet.getString("lastname"));
                user.setFirstname(resultSet.getString("firstname"));
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }

        return user;
    }



    public int getUserId(String login) {
        int groupid = 0;

        String query = "SELECT groupid FROM users WHERE login=?";

        try {
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, login);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                groupid = resultSet.getInt("groupid");
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return groupid;

    }


    public int getGroupId(String login) {
        int groupid = 0;

        String query = "SELECT groupid FROM users WHERE login=?";

        try {
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, login);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                groupid = resultSet.getInt("groupid");
            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return groupid;

    }

}
