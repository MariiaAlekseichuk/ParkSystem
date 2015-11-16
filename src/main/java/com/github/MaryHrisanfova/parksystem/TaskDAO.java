package com.github.MaryHrisanfova.parksystem;
import com.github.MaryHrisanfova.parksystem.Task;
import com.github.MaryHrisanfova.parksystem.DBConnection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.github.MaryHrisanfova.parksystem.DBConnection.getConnection;

/**
 * Created by Маша on 14.11.2015.
 */
public class TaskDAO {
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
/*
    public void addTask(Task task) {
        try {
            String query = "INSERT INTO tasks(id_sender, id_recipient,tasktype,tasktext,isdone,isconfirmed)" +
                    " VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatment = connection.prepareStatement(query);;

            preparedStatment.setInt(1, userBean.getId());
            preparedStatment.setString(2, userBean.getfName());
            preparedStatment.setString(3, userBean.getlName());
            preparedStatment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

*/

}
