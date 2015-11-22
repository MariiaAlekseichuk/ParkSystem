package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.DBConnection;
import com.github.MaryHrisanfova.parksystem.dao.UserDAO;
import com.github.MaryHrisanfova.parksystem.model.Task;
import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.MaryHrisanfova.parksystem.model.User;
import org.apache.log4j.Logger;

/**
 * Сервлет отвечает за добавление задания.
 *
 * @author Маша
 * @since 16.11.2015.
 */
@WebServlet(urlPatterns = "/add_task")
public class AddTaskServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(DBConnection.class);

    private TaskDAO dao;
    private UserDAO daoUser;
    private UserDAO daoCurrentUser;
    private User currentUser;

    /**
     * Конструктор создает объект класса TaskDAO для отправки запросов в БД
     *
     * @see TaskDAO
     */
    public AddTaskServlet() {
        super();
        dao = new TaskDAO();
        daoUser = new UserDAO();
        daoCurrentUser=new UserDAO();
        currentUser=new User();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        request.setCharacterEncoding("utf-8");//Для отправки русских букв

        List<User> users = new ArrayList<User>();
        daoUser.getUsersFLnamesAndID(users);
        request.setAttribute("users", users);

        currentUser=daoCurrentUser.getUserByLogin((String) request.getSession(true).getAttribute("login"));

        request.setAttribute("currentUser", currentUser);

        String action = request.getParameter("action");
        if (action != null) {
            doPost(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("addTask.jsp");
            rd.forward(request, response);
        }
    }


    /**
     * Считывание значений из полей формы после нажатия кнопки.
     * Отправка запроса в БД на добавление пользователя.
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("in post");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");
        String redirect = "addTask.jsp";

        Task task = new Task();

        task.setTasktype(request.getParameter("taskType"));
        task.setTasktext(request.getParameter("taskText"));
        task.setFirstnameOfSender(currentUser.getFirstname());
        task.setLastnameOfSender(currentUser.getLasttname());
        task.setFirstnameOfRecipient(daoUser.getUserById(Integer.parseInt(request.getParameter("FLNamesRecipient"))).getFirstname());
        task.setLastnameOfRecipient(daoUser.getUserById(Integer.parseInt(request.getParameter("FLNamesRecipient"))).getLasttname());

        //Найдем id получателя и отправителя и запишем в поле объекта dao
        if (task.getFirstnameOfSender().equals("") | task.getLastnameOfSender().equals("") |
                task.getTasktype().equals("") | task.getTasktext().equals("") |
                task.getFirstnameOfRecipient().equals("") |
                task.getLastnameOfRecipient().equals("") == false) {
            dao.setIdSender(currentUser.getId());
            dao.setIdRecipient(Integer.parseInt(request.getParameter("FLNamesRecipient")));
            if (dao.getIdSender() != 0 & dao.getIdRecipient() != 0) {
                dao.addTask(task);

                request.setAttribute("taskWasAdded", "✅");


            } else {
                logger.error("Не удалось найти пользователя с указанными ФИО" + task.getFirstnameOfSender() + task.getLastnameOfSender() +
                        " или" +
                        task.getFirstnameOfRecipient() + task.getLastnameOfRecipient());
            }
        }

       RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
        rd.forward(request, response);



    }

}
