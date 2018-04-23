package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.impl.ServiceFactory;

public class AddToCartLogic {
    private static final ProductService productService = ServiceFactory.newProductService();

    public static Product getProductById(Long id) {
        return productService.getById(id);
    }
}
