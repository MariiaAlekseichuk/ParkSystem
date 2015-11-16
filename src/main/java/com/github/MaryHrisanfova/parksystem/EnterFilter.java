package com.github.MaryHrisanfova.parksystem;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Маша on 15.11.2015.
 */

//@WebFilter(urlPatterns = "index.jsp")
public class EnterFilter implements Filter {
    private UserDAO dao;
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

        //@Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            System.out.println("in filter");
            ServletContext context = config.getServletContext();
            dao = new UserDAO();
            // TODO: проверить логин и пароль
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

            String login = httpServletRequest.getParameter("login");
            String password = httpServletRequest.getParameter("password");

            if (dao.isUserCorrect(login, password)) {
                HttpSession session=httpServletRequest.getSession();
                session.setAttribute("login", login);
                //RequestDispatcher dispatcher = context.getRequestDispatcher("tasks.jsp");

                filterChain.doFilter(servletRequest, servletResponse);

                //dispatcher.include(servletRequest, servletResponse);
                //dispatcher.forward(httpServletRequest, httpServletResponse);
               // context.getRequestDispatcher("tasks.jsp").forward(httpServletRequest, httpServletResponse);


            }
            else

                context.getRequestDispatcher("/error.html").forward(httpServletRequest, httpServletResponse);
        }

   // @Override
    public void destroy() {
        //this.filterConfig = null;
    }
}
