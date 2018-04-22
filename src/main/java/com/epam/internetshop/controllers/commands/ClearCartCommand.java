package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ClearCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Product, Long> bucketList = (HashMap<Product, Long>) session.getAttribute("bucketList");
        bucketList.clear();
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUCKET_PAGE_PATH);
    }
}
