package com.github.MaryHrisanfova.parksystem.dao;

import com.github.MaryHrisanfova.parksystem.model.Task;
import com.sun.org.apache.xpath.internal.operations.Bool;

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


    public Task getTaskById(int id,Task task) {
        try{
            String query="SELECT tasks.id,sender.firstname,sender.lastname,  tasks.tasktype,  tasks.tasktext,\n" +
                    "recipient.firstname,recipient.lastname, tasks.isdone, tasks.isconfirmed \n" +
                    "                    FROM tasks \n" +
                    "                    INNER JOIN users AS sender\n" +
                    "                    INNER JOIN users AS recipient \n" +
                    "                    ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient \n" +
                    "                    WHERE tasks.id=?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                task.setTaskid(resultSet.getInt(1));
                task.setFirstnameOfSender(resultSet.getString(2));
                task.setLastnameOfSender(resultSet.getString(3));
                task.setTasktype(resultSet.getString(4));
                task.setTasktext(resultSet.getString(5));
                task.setFirstnameOfRecipient(resultSet.getString(6));
                task.setLastnameOfRecipient(resultSet.getString(7));
                task.setIsdone(resultSet.getBoolean(8));
                task.setIsconfirmed(resultSet.getBoolean(9));
            }


            if (id==0){
                SQLException e;
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {

        }
        return task;
    }





    public List<Task> getAllTasks(List<Task> tasks) {
        // List<Task> tasks = new ArrayList<Task>();
        try {
            String query = "SELECT tasks.id,sender.lastname, sender.firstname, tasks.tasktype,  tasks.tasktext,recipient.lastname, recipient.firstname,tasks.isdone, tasks.isconfirmed \n" +
                    "FROM tasks \n" +
                    " INNER JOIN users AS sender\n" +
                    " INNER JOIN users AS recipient\n" +
                    " ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getBoolean(8), resultSet.getBoolean(9)));

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

    public void delTask(int id) {
        try {
            String query = "DELETE FROM tasks WHERE id=?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id);
            preparedStatment.executeUpdate();
            preparedStatment.close();

        } catch (SQLException e) {
        }
    }

    public void editTask(int id,
                         int id_of_sender,
                         String tasktype,
                         String tasktext,
                         int id_of_recipient,
                         boolean is_done,
                         boolean is_confirmed
    )

           /*

            int id,
                         int id_of_sender,String tasktype,String tasktext,int id_of_recipient, boolean is_done, boolean is_confirmed)
                         */
    {
        try {


           /* String query = "UPDATE tasks \n" +
                    "SET id_sender = (select id from users WHERE firstname=? AND lastname=?),\n" +
                    " id_recipient = (select id from users WHERE firstname=? AND lastname=?),\n" +
                    "    tasktype=?,\n" +
                    "    tasktext=?,\n" +
                    "WHERE id =?";
                    */

            String query = "UPDATE tasks SET id_sender=?,tasktype=?,tasktext=?,id_recipient=?,isdone=?,isconfirmed=? WHERE id =?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id_of_sender);
            preparedStatment.setString(2, tasktype);
            preparedStatment.setString(3, tasktext);
            preparedStatment.setInt(4, id_of_recipient);
            preparedStatment.setBoolean(5, is_done);
            preparedStatment.setBoolean(6, is_confirmed);
            preparedStatment.setInt(7, id);

 /*
            String query = "UPDATE tasks SET id_sender =?,tasktype=?,tasktext=?,id_recipient =?,isdone=?,isconfirmed=? WHERE id =?";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id_of_sender);
            preparedStatment.setString(2, tasktype);
            preparedStatment.setString(3, tasktext);
            preparedStatment.setInt(4, id_of_recipient);
            preparedStatment.setInt(5, id);
            preparedStatment.setBoolean(6, is_done);
            preparedStatment.setBoolean(7, is_confirmed);
*/
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
