package com.epam.internetshop.controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/auth")
public class AuthenticationFilter implements Filter {
    private static boolean requiresAuth(String uri) {
        return !(uri.equals("/index.jsp")
                || uri.startsWith("/db")
                || uri.equals("/login")
                || uri.equals("/register")
                || uri.startsWith("/css")
                || uri.startsWith("/js")
                || uri.startsWith("/images")
                || uri.startsWith("/fonts")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        boolean loggedIn = !(session == null || session.getAttribute("login") == null);
        if (!loggedIn && requiresAuth(uri))
            resp.sendRedirect("/index.jsp");
        else if (loggedIn && uri.equals("/index.jsp"))
            resp.sendRedirect("/logined.jsp"); /* What a crutch! */
        else
            filterChain.doFilter(req, resp);
    }
}
