package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.TaskDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 16.11.2015.
 */
@WebServlet(urlPatterns = "/delTask")
public class DelTaskServlet extends HttpServlet {
    private TaskDAO dao;
    int id;

    public DelTaskServlet() {
        super();
        dao = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("utf-8");//Для отправки русских букв
        id=0;
        //request.setAttribute("taskWasAdded", "");
        // id = Integer.parseInt(request.getParameter("id"));
        //  System.out.println("id from get"+id);
        //  request.getParameter("id");
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
        System.out.println("in post");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        request.setAttribute("taskWasAdded", "");
        //request.setAttribute("taskWasDeleted", "");
        String redirect = "delTask.jsp";


        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);

        dao.delTask(id);
        System.out.println("id from post"+id);
    }
}
        //request.setAttribute("taskWasDeleted", "Задача удалена");
    /*
                //  request.setAttribute("user", dao.getUserIdByFLnames(request.getParameter("firstName"), request.getParameter("lastName")));
            }
             if (action.equalsIgnoreCase("delete")){
             String action = request.getParameter("action");
             }
*/




