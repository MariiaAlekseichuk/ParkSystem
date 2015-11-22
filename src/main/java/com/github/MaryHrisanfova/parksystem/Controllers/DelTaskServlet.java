package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет отвечает за удаление задания.
 * @author Маша
 * @since 16.11.2015.
 */
@WebServlet(urlPatterns = "/delete_task")
public class DelTaskServlet extends HttpServlet {
    private TaskDAO dao;
    int id;

    /**
     * Конструктор создает объект класса TaskDAO для отправки запросов в БД
     * @see TaskDAO
     */
    public DelTaskServlet() {
        super();
        dao = new TaskDAO();
    }

    /**
     * После нажатия кнопки - отправка на страницу delTask.jsp для подтверждения удаления.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute("login");
        Integer groupid = (Integer) session.getAttribute("groupid");
        request.setAttribute("groupid", groupid);

        request.setAttribute("taskWasDeleted", "");
        request.setCharacterEncoding("utf-8");//Для отправки русских букв
        id = 0;

        System.out.println("in get");
        if (id == 0) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String action = request.getParameter("action");
        if (action != null)
            doPost(request, response);
        else {
            RequestDispatcher rd = request.getRequestDispatcher("delTask.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        String redirect = "delTask.jsp";

        request.setAttribute("taskWasDeleted", "✅");
        dao.delTask(id);
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);

    }
}





