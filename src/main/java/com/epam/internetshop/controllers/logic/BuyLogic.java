package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.manager.ServiceFactory;

import java.util.HashMap;

public class BuyLogic {
    private static final ProductService productService = ServiceFactory.newProductService();
    private static final PaymentService paymentService = ServiceFactory.newPaymentService();

    public static Product getProductById(Long id) {
        return productService.getById(id);
    }

    public static void performPayment(User user, HashMap<Product, Long> poductCount) {
        paymentService.performPayment(user.getLogin(), poductCount);
    }
}
