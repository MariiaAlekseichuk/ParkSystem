package com.github.MaryHrisanfova.parksystem;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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

import static com.github.MaryHrisanfova.parksystem.DBConnection.getConnection;

/**
 * Created by Маша on 14.11.2015.
 */
@WebServlet(urlPatterns = "/tasks")
public class SeeTasksServlet extends HttpServlet {

    private Connection connection;
    private Statement statement;
    private ServletConfig config;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        ServletContext context = getServletContext();

        try {
            connection = getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


      //  res.setContentType("text/html");
/*
        try{
            req.getRequestDispatcher("tasks.jsp").include(req,res);
            System.out.println("Ура");}
        catch (IOException e){

        }
        */
        res.setContentType("text/html");
        /*
        try{
            req.getRequestDispatcher("index.jsp").include(req, res);
            System.out.println("Ура");}
        catch (IOException e){

        }

*/
        HttpSession session=req.getSession(false);
        String login=(String)session.getAttribute("login");
        System.out.println(login);
        if(login!=null){
            System.out.println(login+"сессия здесь");//пойдет в лог
        };

        List <Task> tasks=new ArrayList<Task>();
        try {
            String query = "SELECT sender.lastname, sender.firstname, tasks.tasktype,  tasks.tasktext,recipient.lastname, recipient.firstname,tasks.isdone, tasks.isconfirmed \n" +
                    "FROM tasks \n" +
                    " INNER JOIN users AS sender\n" +
                    " INNER JOIN users AS recipient\n" +
                    " ON sender.id=tasks.id_sender AND recipient.id=tasks.id_recipient";
            PreparedStatement preparedStatment = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatment.executeQuery();

                while (resultSet.next()) {
                tasks.add(new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getBoolean(7), resultSet.getBoolean(8)));

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
        req.setAttribute("tasks", tasks);

        try {
            context.getRequestDispatcher("/tasks.jsp").forward(req, res);
           // req.getRequestDispatcher("tasks.jsp").forward(req, res);
        } catch (IOException e) {

        }

    }
}
