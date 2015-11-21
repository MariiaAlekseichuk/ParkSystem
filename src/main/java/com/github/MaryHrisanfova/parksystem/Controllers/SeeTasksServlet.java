package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.model.Task;
import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;

import javax.naming.NamingException;
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

import static com.github.MaryHrisanfova.parksystem.dao.DBConnection.getConnection;

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


        List<Task> tasks = new ArrayList<Task>();

        TaskDAO dao = new TaskDAO();

        HttpSession session = req.getSession(true);
        String login = (String) session.getAttribute("login");
        Integer groupid = (Integer) session.getAttribute("groupid");

        req.setAttribute("groupid", groupid);
        if(groupid==2){
            tasks = dao.getTasksForUser(tasks,login);
            System.out.println(tasks);
            if (tasks.isEmpty()){
                req.setAttribute("tasksare","Для Вас нет задач");
            }
            else{
                req.setAttribute("tasksare","Ваши задачи");
            }
        }
        if (groupid==1) {
            req.setAttribute("tasksare","Все задачи");
            tasks = dao.getAllTasks(tasks);
        }
        req.setAttribute("tasks", tasks);

        try {
            context.getRequestDispatcher("/tasks.jsp").forward(req, res);
        } catch (IOException e) {


        }
    }
}
