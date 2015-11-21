package com.github.MaryHrisanfova.parksystem.controllers;

import com.github.MaryHrisanfova.parksystem.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 17.11.2015.
 */
public class SeePagesFilter implements Filter {
    private FilterConfig config;


    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    //@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("in filter");
        ServletContext context = config.getServletContext();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession(true);

        System.out.println("session="+session);

        if (session == null) {
            context.getRequestDispatcher("/error.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            String login = (String) session.getAttribute("login");
            System.out.println("login from filter" + login);
            Integer groupid = (Integer) session.getAttribute("groupid");
            session.getAttribute("local");
            //httpServletRequest.setAttribute("groupid", groupid);
            if (login != null & groupid != null) {
                filterChain.doFilter(servletRequest, servletResponse);

            }
            else {
                context.getRequestDispatcher("/error.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }


    }


    // @Override
    public void destroy() {
        this.config = null;
    }
}

