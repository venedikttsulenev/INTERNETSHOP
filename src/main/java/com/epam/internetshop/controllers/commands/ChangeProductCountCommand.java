package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.ChangeProductCountLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeProductCountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long productId = Long.valueOf(request.getParameter("productId"));
        Long newCount = Long.valueOf(request.getParameter("productCount"));
        if (!ChangeProductCountLogic.changeProductCount(productId, newCount))
            request.getSession().setAttribute("changeCountMessage", "Internal error: Could not perform operation");

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PAGE_PATH);
    }
}
