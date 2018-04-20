package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
public class RemoveFromCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Long, Integer> bucketList = (HashMap<Long, Integer>) session.getAttribute("bucketList");
        Long productId = (Long) request.getAttribute("productId");
        bucketList.remove(productId);
        session.removeAttribute("productId");
        /* Do we need to re-set bucketList attribute since it was updated? */
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUCKET_PAGE_PATH);
    }
}
