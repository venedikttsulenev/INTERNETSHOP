package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.UserException;
import com.epam.internetshop.services.manager.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReplenishBalanceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            Long amount = Long.valueOf(request.getParameter("amount"));

            User user = (User) session.getAttribute("user");

            user = ServiceFactory.newUserService().increaseAccount(user.getLogin(), amount);
            session.setAttribute("user", user); /* Update user data */
        } catch (UserException e) {
            session.setAttribute("message", e.getMessage());
        } catch (NumberFormatException e) {
            session.setAttribute("message", "You are not supposed to add such amounts of money");
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PAGE_PATH);
    }
}
