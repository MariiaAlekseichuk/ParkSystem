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

import com.github.MaryHrisanfova.parksystem.TaskDAO;

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

        res.setContentType("text/html");

        HttpSession session = req.getSession(false);
        String login = (String) session.getAttribute("login");
        System.out.println(login);
        if (login != null) {
            System.out.println(login + "сессия здесь");//пойдет в лог
        }
        ;

        List<Task> tasks = new ArrayList<Task>();

        TaskDAO dao = new TaskDAO();
        tasks = dao.getAllTasks(tasks);
        req.setAttribute("tasks", tasks);

        try {
            context.getRequestDispatcher("/tasks.jsp").forward(req, res);
            // req.getRequestDispatcher("tasks.jsp").forward(req, res);
        } catch (IOException e) {


        }
    }
}
