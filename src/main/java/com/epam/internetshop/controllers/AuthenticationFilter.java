package com.epam.internetshop.controllers;

import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@WebFilter("/auth")
public class AuthenticationFilter implements Filter {

    private static final ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    private static final HashSet<String> legitURIs = new HashSet<>(
            Arrays.asList(
                    configurationManager.getProperty(ConfigurationManager.INDEX_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.BUCKET_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.PROFILE_PAGE_PATH),

                    configurationManager.getProperty(ConfigurationManager.LOGIN_ACTION),
                    configurationManager.getProperty(ConfigurationManager.REGISTER_ACTION),
                    configurationManager.getProperty(ConfigurationManager.PROCESS_ACTION)

            )
    );

    private static final HashSet<String> requireAuthURIs = new HashSet<>(
            Arrays.asList(
                    configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.BUCKET_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.PROFILE_PAGE_PATH)
            )
    );

    private static final HashSet<String> requireUnathURIs = new HashSet<>(
            Arrays.asList(
                    configurationManager.getProperty(ConfigurationManager.INDEX_PAGE_PATH),
                    configurationManager.getProperty(ConfigurationManager.LOGIN_ACTION),
                    configurationManager.getProperty(ConfigurationManager.REGISTER_ACTION)
            )
    );

    private static boolean isLegitUri(String uri) {
        return uri.startsWith(configurationManager.getProperty(ConfigurationManager.DB_PATH))
                || uri.startsWith(configurationManager.getProperty(ConfigurationManager.CSS_PATH))
                || uri.startsWith(configurationManager.getProperty(ConfigurationManager.JS_PATH))
                || uri.startsWith(configurationManager.getProperty(ConfigurationManager.IMAGES_PATH))
                || uri.startsWith(configurationManager.getProperty(ConfigurationManager.FONTS_PATH))
                || legitURIs.contains(uri);
    }

    private static boolean requiresAuth(String uri) {
        return requireAuthURIs.contains(uri);
    }

    private static boolean requiresUnauth(String uri) {
        return requireUnathURIs.contains(uri);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        boolean loggedIn = !(session == null || session.getAttribute("login") == null);
        if (!loggedIn && (!isLegitUri(uri) || requiresAuth(uri)))
            resp.sendRedirect(
                    configurationManager.getProperty(ConfigurationManager.INDEX_PAGE_PATH)
            );
        else if (loggedIn && (!isLegitUri(uri) || requiresUnauth(uri)))
            resp.sendRedirect(
                    configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH)
            ); /* What a crutch! */
        else
            filterChain.doFilter(req, resp);
    }
}
