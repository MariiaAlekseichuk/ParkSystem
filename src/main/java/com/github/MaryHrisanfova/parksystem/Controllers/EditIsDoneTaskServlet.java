package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;
import com.github.MaryHrisanfova.parksystem.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.MaryHrisanfova.parksystem.model.Task.getIsconfirmedStatic;
import static com.github.MaryHrisanfova.parksystem.model.Task.getIsdoneStatic;

/**
 * Created by Маша on 16.11.2015.
 */
@WebServlet(urlPatterns = "/edit_is_task_done")
public class EditIsDoneTaskServlet extends HttpServlet {




    private TaskDAO dao;
    Task task = new Task();
    int id;

    public EditIsDoneTaskServlet() {
        super();
        dao = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        request.setCharacterEncoding("utf-8");//Для отправки русских букв
        request.setAttribute("taskWasEdited", "");
        id=0;

        System.out.println("in get");
        System.out.println("id===" + id);
        if (id == 0) {
            id = Integer.parseInt(request.getParameter("id"));

        }
        System.out.println("id before" + id);

        task=dao.getTaskById(id,task);
        request.setAttribute("task", task);
        System.out.println("fn get" + request.getParameter("firstnameOfSender"));
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            System.out.println("я ушел");
            doPost(request, response);

        }
        else {
            System.out.println("я в элс");
            RequestDispatcher rd = request.getRequestDispatcher("editIsDone.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in post");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");
        //request.setAttribute("taskWasDeleted", "");
        String redirect = "editTask.jsp";

        int id_of_sender=dao.getUserIdByFLnames(request.getParameter("firstnameOfSender"), request.getParameter("lastnameOfSender"));
        System.out.println(id_of_sender);
        int id_of_recipient=dao.getUserIdByFLnames(request.getParameter("firstnameOfRecipient"), request.getParameter("lastnameOfRecipient"));

        dao.editIsDoneTask(id, getIsdoneStatic(request.getParameter("isdone")));
        request.setAttribute("taskWasEdited", "✅");
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }
}