package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.NewProductLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        Long price = Long.valueOf(request.getParameter("productPrice"));
        Long available = Long.valueOf(request.getParameter("productAvailable"));

        HttpSession session = request.getSession();
        if (null == NewProductLogic.createProduct(name, description, available, price))
            session.setAttribute("newProductMessage", "Internal error: not create new product. Please, retry.");
        else
            session.setAttribute("newProductMessage", "Success");

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PROFILE_PAGE_PATH);
    }
}
