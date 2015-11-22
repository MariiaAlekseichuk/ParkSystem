package com.github.MaryHrisanfova.parksystem.controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Берет из сессии атрибуты login,groupid
 * Отправляет в response атрибуты всем остальным сервлетам
 * @author Маша
 * @since 17.11.2015.
 */
public class SeePagesFilter implements Filter {
    private FilterConfig config;


    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ServletContext context = config.getServletContext();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession(true);

        if (session == null) {
            context.getRequestDispatcher("/error.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            String login = (String) session.getAttribute("login");

            Integer groupid = (Integer) session.getAttribute("groupid");
            session.getAttribute("local");

            if (login != null) {

                filterChain.doFilter(servletRequest, servletResponse);

            } else {

                httpServletRequest.getRequestDispatcher("/error.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }


    }

    public void destroy() {
        this.config = null;
    }
}

