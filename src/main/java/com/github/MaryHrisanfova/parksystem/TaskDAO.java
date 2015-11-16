package com.github.MaryHrisanfova.parksystem;

import com.github.MaryHrisanfova.parksystem.Task;
import com.github.MaryHrisanfova.parksystem.DBConnection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.github.MaryHrisanfova.parksystem.DBConnection.getConnection;

/**
 * Created by Маша on 14.11.2015.
 */
public class TaskDAO {
    private int idSender;
    private int idRecipient;
    private Connection connection;


    public TaskDAO() {
        try {
            connection = getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks(List<Task> tasks) {
        // List<Task> tasks = new ArrayList<Task>();
        try {
            String query = "SELECT sender.lastname, sender.firstname, tasks.tasktype,  tasks.tasktext,recipient.lastname, recipient.firstname,tasks.isdone, tasks.isconfirmed \n" +
                    "FROM tasks \n" +
                    " INNER JOIN users AS sender\n" +
                    " INNER JOIN users AS recipient\n" +
                    " ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getBoolean(8)));

            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {

        }
        return tasks;
    }

    public int getUserIdByFLnames(String firstname, String lastname) {
        int id=0;

        try {
            String query = "SELECT id from users WHERE firstname=? AND lastname=?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, firstname);
            preparedStatment.setString(2, lastname);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                System.out.println("я нашел ид" + id);
            }

            if (id==0){

                SQLException e;
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {

        }

        return id;
    }


    public void addTask(Task task) {
        try {
            String query = "INSERT INTO tasks (id_sender,id_recipient,tasktype,tasktext) VALUES (?,?,?,?)";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, getIdSender());
            preparedStatment.setInt(2, getIdRecipient());
            System.out.println("id rec=" + getIdRecipient());
            System.out.println("id send=" + getIdSender());
            preparedStatment.setString(3, task.getTasktype());
            preparedStatment.setString(4, task.getTasktext());
            preparedStatment.executeUpdate();

            preparedStatment.close();

        } catch (SQLException e) {

        }
    }

    public int getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }
}
