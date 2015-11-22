package com.github.MaryHrisanfova.parksystem.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Выход их системы
 *
 * @author Маша
 * @since 16.11.2015.
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //doPost(request, response);
        System.out.println("1");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/index");
        rd.forward(request, response);
    }


}
