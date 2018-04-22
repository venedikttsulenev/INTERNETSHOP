package com.epam.internetshop.controllers.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.internetshop.controllers.logic.LoginLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;
import com.epam.internetshop.services.manager.ServiceFactory;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        String page = null;
        if (LoginLogic.login(login, pass)) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("isAdmin", ServiceFactory.newUserService().isAdmin(login));
            page = configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", messageManager.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = configurationManager.getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        }
        return page;
    }
}
