package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.AddToCartLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        HashMap<Product, Long> bucketList = (HashMap<Product, Long>) session.getAttribute("bucketList");
        Long productId = Long.valueOf(request.getParameter("productId"));
        Long productCount = Long.valueOf(request.getParameter("productCount"));

        Product product = AddToCartLogic.getProductById(productId);
        bucketList.put(product, productCount);

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}