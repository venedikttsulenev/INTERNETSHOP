package com.epam.internetshop.controllers.commands;

import com.epam.internetshop.controllers.manager.ConfigurationManager;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.manager.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class BuyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Long, Long> bucketList = (HashMap<Long, Long>) session.getAttribute("bucketList");
        PaymentService paymentService = ServiceFactory.newPaymentService();
        String login = (String) session.getAttribute("login");
        paymentService.performPayment(login, bucketList);
        bucketList.clear();
        session.setAttribute("bucketList", bucketList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUCKET_PAGE_PATH);
    }
}
