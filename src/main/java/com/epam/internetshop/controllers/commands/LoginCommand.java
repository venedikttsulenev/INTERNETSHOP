package com.epam.internetshop.controllers.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.internetshop.controllers.logic.LoginLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;

import java.util.HashMap;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        MessageManager messageManager = MessageManager.getInstance();

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        String page = null;
        User user;
        if (null != (user = LoginLogic.login(login, pass))) {
            session.setAttribute("user", user);

            HashMap<Product, Long> bucketList = new HashMap<>();
            session.setAttribute("bucketList", bucketList);

            page = configurationManager.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", messageManager.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = configurationManager.getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        }
        return page;
    }
}
