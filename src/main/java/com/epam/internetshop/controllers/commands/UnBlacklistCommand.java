package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.BlacklistLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnBlacklistCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String userLogin = request.getParameter("userLogin");

        if (userLogin == null || !BlacklistLogic.unBlackListUser(userLogin))
            session.setAttribute("BLMessage", "Error: could not unblacklist user. Please, retry");

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PAGE_PATH);
    }
}
