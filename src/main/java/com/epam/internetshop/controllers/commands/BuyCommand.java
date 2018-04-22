package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.logic.BuyLogic;
import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.controllers.manager.MessageManager;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class BuyCommand implements Command {

    private String parameterPostfixForIndex(int i, HttpServletRequest request) {
        String iStr = String.valueOf(i);
        if (null == request.getParameter("productId" + iStr)
                || null == request.getParameter("productCount" + iStr)
                || null == request.getParameter("productIncluded" + iStr))
            return null;
        return iStr;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        HashMap<Product, Long> finalBucketList = new HashMap<>();
        String iStr = parameterPostfixForIndex(0, request);
        for (int i = 0; iStr != null; ++i) {
            Boolean productIncluded = Boolean.valueOf(request.getParameter("productIncluded" + iStr));
            if (productIncluded) {
                Long productId = Long.valueOf(request.getParameter("productId" + iStr));
                Long productCount = Long.valueOf(request.getParameter("productCount" + iStr));

                Product product = BuyLogic.getProductById(productId);

                finalBucketList.put(product, productCount);
            }
            iStr = parameterPostfixForIndex(i, request);
        }

        User user = (User) session.getAttribute("user");

        try {
            BuyLogic.performPayment(user, finalBucketList);
            HashMap<Product, Long> bucketList = (HashMap<Product, Long>) session.getAttribute("bucketList");
            bucketList.clear();
            session.setAttribute("message", MessageManager.getInstance().getProperty(MessageManager.BUY_SUCCESS_MESSAGE));
        } catch (UserException | ProductException e) {
            session.setAttribute("message", e.getMessage());
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUCKET_PAGE_PATH);
    }
}
