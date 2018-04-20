package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.RegisterLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");

        String page = null;
        if (pass.equals(pass2) && !RegisterLogic.loginIsAlreadyTaken(login)) {
            if (null != RegisterLogic.registerUser(login, pass)) {
                request.getSession().setAttribute("login", login);
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
