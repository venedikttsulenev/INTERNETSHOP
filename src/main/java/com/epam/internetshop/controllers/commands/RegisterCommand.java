package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.RegisterLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        String page = null;
        if (!RegisterLogic.loginIsAlreadyTaken(login)) {
            if (null != RegisterLogic.registerUser(login, pass)) {
                request.setAttribute("username", login);
                page = configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            } else {
                request.setAttribute("errorMessage", messageManager.getProperty(MessageManager.REGISTER_ERROR_MESSAGE));
                page = configurationManager.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        } else {
            request.setAttribute("errorMessage", messageManager.getProperty(MessageManager.LOGIN_TAKEN_ERROR_MESSAGE));
            page = configurationManager.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
