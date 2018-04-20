package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Long, Long> bucketList = (HashMap<Long, Long>) session.getAttribute("bucketList");
        Long productId = (Long) request.getAttribute("productId");
        Long productCount = (Long) request.getAttribute("productCount");
        bucketList.put(productId, productCount);
//        session.setAttribute("bucketList", bucketList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
