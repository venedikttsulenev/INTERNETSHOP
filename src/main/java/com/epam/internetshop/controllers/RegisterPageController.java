package com.epam.internetshop.controllers;

import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterPageController extends HttpServlet implements javax.servlet.Servlet {

    public RegisterPageController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();

        String page = configurationManager.getProperty(ConfigurationManager.REGISTER_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }
}
