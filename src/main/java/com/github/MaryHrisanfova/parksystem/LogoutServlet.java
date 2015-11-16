package com.github.MaryHrisanfova.parksystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Маша on 16.11.2015.
 */
@WebServlet (urlPatterns ="/index")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //request.getRequestDispatcher("index.jsp").include(request, response);
        //response.sendRedirect("EnterFilter");
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
