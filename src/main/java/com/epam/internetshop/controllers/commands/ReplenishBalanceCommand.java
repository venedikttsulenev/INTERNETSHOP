package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.ReplenishBalanceLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReplenishBalanceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String amountParameter = request.getParameter("amount");
        if (null == amountParameter)
            session.setAttribute("message", "Error: amount of money is not specified");
        else if (amountParameter.length() == 0)
            session.setAttribute("message", "Please, enter the sum");
        else {
            try {
                Long amount = Long.valueOf(amountParameter);
                User user = (User) session.getAttribute("user");

                user = ReplenishBalanceLogic.replenishBalance(user, amount);

                session.setAttribute("user", user); /* Update user data */
            } catch (UserException e) {
                session.setAttribute("message", e.getMessage());
            } catch (NumberFormatException e) {
                session.setAttribute("message", "You are not supposed to add such amounts of money");
            }
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PAGE_PATH);
    }
}
