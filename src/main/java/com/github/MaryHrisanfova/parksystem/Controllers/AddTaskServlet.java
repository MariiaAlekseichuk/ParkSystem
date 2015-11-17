package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.model.Task;
import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Маша on 16.11.2015.
 */
@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {
    private TaskDAO dao;

    public AddTaskServlet() {
        super();
        dao = new TaskDAO();
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//Для отправки русских букв

        doPost(request, response);

    }
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");
        String redirect = "addTask.jsp";

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("insert")) {
            Task task = new Task();

            task.setTasktype(request.getParameter("taskType"));
            task.setTasktext(request.getParameter("taskText"));
            task.setFirstnameOfSender(request.getParameter("firstNameOfSender"));
            task.setLastnameOfSender(request.getParameter("lastNameOfSender"));
            task.setFirstnameOfRecipient(request.getParameter("firstNameOfRecipient"));
            task.setLastnameOfRecipient(request.getParameter("lastNameOfRecipient"));

            //Найдем id получателя и отправителя и запишем в поле объекта dao
            if (task.getFirstnameOfSender().equals("") | task.getLastnameOfSender().equals("") |
                    task.getTasktype().equals("") | task.getTasktext().equals("") |
                    task.getFirstnameOfRecipient().equals("") |
                    task.getLastnameOfRecipient().equals("") == false) {
                dao.setIdSender(dao.getUserIdByFLnames(task.getFirstnameOfSender(), task.getLastnameOfSender()));
                dao.setIdRecipient(dao.getUserIdByFLnames(task.getFirstnameOfRecipient(), task.getLastnameOfRecipient()));
                if (dao.getIdSender() != 0 & dao.getIdRecipient() != 0) {
                    dao.addTask(task);
                    request.setAttribute("taskWasAdded", "Задача добавлена");
                }
                //  request.setAttribute("user", dao.getUserIdByFLnames(request.getParameter("firstName"), request.getParameter("lastName")));
            }

        }
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

}
