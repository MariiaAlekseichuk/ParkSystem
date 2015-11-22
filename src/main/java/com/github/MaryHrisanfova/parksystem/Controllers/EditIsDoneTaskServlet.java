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
import static com.github.MaryHrisanfova.parksystem.model.Task.getIsdoneStatic;

/**
 Сервлет отвечает за изменение статуса задания.
 * @author Маша
 * @since 16.11.2015.
 */
@WebServlet(urlPatterns = "/edit_is_task_done")
public class EditIsDoneTaskServlet extends HttpServlet {

    /**
     * Конструктор создает объект класса TaskDAO для отправки запросов в БД
     * @see TaskDAO
     */
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
        id = 0;

        if (id == 0) {
            id = Integer.parseInt(request.getParameter("id"));

        }

        task = dao.getTaskById(id, task);
        request.setAttribute("task", task);

        String action = request.getParameter("action");

        if (action != null) {
            System.out.println("я ушел");
            doPost(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("editIsDone.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");
        //request.setAttribute("taskWasDeleted", "");
        String redirect = "editTask.jsp";

        dao.editIsDoneTask(id, getIsdoneStatic(request.getParameter("isdone")));
        request.setAttribute("taskWasEdited", "✅");
        RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
        rd.forward(request, response);
    }
}