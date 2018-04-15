package com.epam.internetshop.controllers.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.internetshop.controllers.logic.LoginLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        String page = null;
        if (LoginLogic.userExists(login, pass)) {
            request.setAttribute("username", login);
            page = configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", messageManager.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = configurationManager.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
