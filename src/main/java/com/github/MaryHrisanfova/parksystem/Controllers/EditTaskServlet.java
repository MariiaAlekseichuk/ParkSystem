package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;
import com.github.MaryHrisanfova.parksystem.dao.UserDAO;
import com.github.MaryHrisanfova.parksystem.model.Task;
import com.github.MaryHrisanfova.parksystem.model.User;

import static com.github.MaryHrisanfova.parksystem.model.Task.*;

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

/**
 * Сервлет отвечает за редактирование задания владельцем парка.
 *
 * @author Маша
 * @since 16.11.2015.
 */
@WebServlet(urlPatterns = "/edit_task")
public class EditTaskServlet extends HttpServlet {


    private TaskDAO dao;
    private UserDAO daoUser;
    private UserDAO daoCurrentUser;
    private User currentUser;
    Task task = new Task();
    int id;
    private User user;

    /**
     * Конструктор создает объект класса TaskDAO для отправки запросов в БД
     *
     * @see TaskDAO
     */
    public EditTaskServlet() {
        super();
        dao = new TaskDAO();
        daoCurrentUser = new UserDAO();
        daoUser = new UserDAO();
        currentUser = new User();
        user = new User();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        request.setCharacterEncoding("utf-8");//Для отправки русских букв
        request.setAttribute("taskWasEdited", "");

        List<User> users = new ArrayList<User>();
        daoUser.getUsersFLnamesAndID(users);
        request.setAttribute("users", users);

        id = 0;

        if (id == 0) {
            id = Integer.parseInt(request.getParameter("id"));

        }

        int id_recipient = dao.getIdRecipientByTaskID(id);
        request.setAttribute("id_recipient", id_recipient);
        System.out.println("id before" + id);

        task = dao.getTaskById(id, task);
        request.setAttribute("task", task);

        currentUser = daoCurrentUser.getUserByLogin((String) request.getSession(true).getAttribute("login"));

        request.setAttribute("currentUser", currentUser);

        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            doPost(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("editTask.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");

        String redirect = "editTask.jsp";
        /**
         * Находим id отправителя и исполнителя задания
         */



       // int id_of_recipient = dao.getUserIdByFLnames(request.getParameter("firstnameOfRecipient"), request.getParameter("lastnameOfRecipient"));

        dao.setIdRecipient(Integer.parseInt(request.getParameter("FLNamesRecipient")));

        dao.editTask(id,
                currentUser.getId(),
                request.getParameter("tasktype"),
                request.getParameter("tasktext"),
                dao.getIdRecipient(),
                getIsdoneStatic(request.getParameter("isdone")),
                getIsconfirmedStatic(request.getParameter("isconfirmed"))
        );
        request.setAttribute("taskWasEdited", "✅");
        RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
        rd.forward(request, response);
    }
}