package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 15.11.2015.
 */


public class EnterFilter implements Filter {
    private UserDAO dao;
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("in filter");
        ServletContext context = config.getServletContext();
        dao = new UserDAO();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");

        if (dao.isUserCorrect(login, password)) {
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("login", login);
            session.setAttribute("groupid", dao.getGroupId(login));

            filterChain.doFilter(servletRequest, servletResponse);

        } else

            context.getRequestDispatcher("/error.jsp").forward(httpServletRequest, httpServletResponse);
    }


    public void destroy() {
        this.config = null;
    }
}
