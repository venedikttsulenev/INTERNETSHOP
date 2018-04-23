package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.manager.ServiceFactory;

public class ChangeProductCountLogic {
    private static final ProductService productService = ServiceFactory.newProductService();

    public static boolean changeProductCount(Long productId, Long newCount) throws ProductException {
        return (null != productService.setCount(productId, newCount));
    }
}
