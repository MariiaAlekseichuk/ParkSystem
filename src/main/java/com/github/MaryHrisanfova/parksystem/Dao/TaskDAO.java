package com.github.MaryHrisanfova.parksystem.dao;

import com.github.MaryHrisanfova.parksystem.model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import static com.github.MaryHrisanfova.parksystem.dao.DBConnection.getConnection;

/**Класс для извлечения информации о заданиях,
 * содержащейся в таблицах park.users и park.task.
 * Связан с классом Task - часть Domain model.
 * Все методы класса содержат соответсвующий SQL-запрос
 * @see Task
 * @author Маша
 * @since 14.11.2015.
 */
public class TaskDAO {
    final static Logger logger = Logger.getLogger(DBConnection.class);

    private int idSender;
    private int idRecipient;
    private Connection connection;

    /**Конструктор создает подключение к пулу соединений
     */
    public TaskDAO() {
        connection = getConnection();
    }


    public Task getTaskById(int id, Task task) {

        String query = "SELECT tasks.id,sender.firstname,sender.lastname,  tasks.tasktype,  tasks.tasktext,\n" +
                "recipient.firstname,recipient.lastname, tasks.isdone, tasks.isconfirmed \n" +
                "                    FROM tasks \n" +
                "                    INNER JOIN users AS sender\n" +
                "                    INNER JOIN users AS recipient \n" +
                "                    ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient \n" +
                "                    WHERE tasks.id=?";

        try {
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


            if (id == 0) {
                logger.error("Не удалось выполнить SQL-запрос" + query + ". id=0");
                return null;
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return task;
    }


    public List<Task> getAllTasks(List<Task> tasks) {

        String query = "SELECT tasks.id,sender.lastname, sender.firstname, tasks.tasktype,  tasks.tasktext,recipient.lastname, recipient.firstname,tasks.isdone, tasks.isconfirmed \n" +
                "FROM tasks \n" +
                " INNER JOIN users AS sender\n" +
                " INNER JOIN users AS recipient\n" +
                " ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient";
        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getBoolean(8), resultSet.getBoolean(9)));

            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return tasks;
    }


    public List<Task> getTasksForUser(List<Task> tasks, String login) {

        String query = "SELECT tasks.id,sender.lastname, sender.firstname, tasks.tasktype,  tasks.tasktext,recipient.lastname, recipient.firstname,tasks.isdone, tasks.isconfirmed \n" +
                "FROM tasks \n" +
                " INNER JOIN users AS sender\n" +
                " INNER JOIN users AS recipient\n" +
                " ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient AND recipient.login=?";
        try {


            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, login);
            ResultSet resultSet = preparedStatment.executeQuery();

            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getBoolean(8), resultSet.getBoolean(9)));

            }
            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return tasks;
    }


    public int getUserIdByFLnames(String firstname, String lastname) {
        int id = 0;
        String query = "SELECT id from users WHERE firstname=? AND lastname=?";
        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setString(1, firstname);
            preparedStatment.setString(2, lastname);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            if (id == 0) {

                SQLException e;
            }

            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }

        return id;
    }


    public void addTask(Task task) {

        String query = "INSERT INTO tasks (id_sender,id_recipient,tasktype,tasktext) VALUES (?,?,?,?)";

        try {
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
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }

    public void delTask(int id) {

        String query = "DELETE FROM tasks WHERE id=?";

        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id);
            preparedStatment.executeUpdate();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }

    public void editTask(int id,
                         int id_of_sender,
                         String tasktype,
                         String tasktext,
                         int id_of_recipient,
                         boolean is_done,
                         boolean is_confirmed
    ) {
        String query = "UPDATE tasks SET id_sender=?,tasktype=?,tasktext=?,id_recipient=?,isdone=?,isconfirmed=? WHERE id =?";

        try {


           /* Вложенный запрос не сработал
           String query = "UPDATE tasks \n" +
                    "SET id_sender = (select id from users WHERE firstname=? AND lastname=?),\n" +
                    " id_recipient = (select id from users WHERE firstname=? AND lastname=?),\n" +
                    "    tasktype=?,\n" +
                    "    tasktext=?,\n" +
                    "WHERE id =?";
                    */


            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id_of_sender);
            preparedStatment.setString(2, tasktype);
            preparedStatment.setString(3, tasktext);
            preparedStatment.setInt(4, id_of_recipient);
            preparedStatment.setBoolean(5, is_done);
            preparedStatment.setBoolean(6, is_confirmed);
            preparedStatment.setInt(7, id);

            preparedStatment.executeUpdate();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }


    public void editIsDoneTask(int id, boolean is_done) {

        String query = "UPDATE tasks SET isdone=? WHERE id =?";
        try {
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setBoolean(1, is_done);
            preparedStatment.setInt(2, id);

            preparedStatment.executeUpdate();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
    }

    public int getIdRecipientByTaskID(int id) {
        String query = "SELECT id_recipient from tasks WHERE id=?";
        int id_recipient = 0;
        try {

            PreparedStatement preparedStatment = connection.prepareStatement(query);
            preparedStatment.setInt(1, id);
            ResultSet resultSet = preparedStatment.executeQuery();
            while (resultSet.next()) {
                id_recipient = resultSet.getInt("id_recipient");
            }


            resultSet.close();
            preparedStatment.close();

        } catch (SQLException e) {
            logger.error("Не удалось выполнить SQL-запрос" + query);
        }
        return id_recipient;
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
