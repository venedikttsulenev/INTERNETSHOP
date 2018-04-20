package com.epam.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.internetshop.controllers.commands.Command;
import com.epam.internetshop.controllers.manager.MessageManager;
import com.epam.internetshop.controllers.manager.ConfigurationManager;

@WebServlet({"/login", "/register", "/process"})
public class Controller extends HttpServlet implements javax.servlet.Servlet {

    private RequestHelper requestHelper = RequestHelper.getInstance();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();
        String page = null;
        try {
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute(
                    "errorMessage",
                    messageManager.getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE)
            );
            page = configurationManager.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute(
                    "errorMessage",
                    messageManager.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE)
            );
            page = configurationManager.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//        dispatcher.forward(request, response);
        response.sendRedirect(page);
    }
}
