package com.github.MaryHrisanfova.parksystem.controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 21.11.2015.
 */
public class LenguageFilter implements Filter {
    private FilterConfig config;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // ServletContext context = config.getServletContext();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession(true);

        String local = httpServletRequest.getParameter("local");
        session.setAttribute("local", local);
        session.getAttribute(local);
        System.out.println("я в денгвидж фильтр и локал="+local);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.config = null;
    }
}
