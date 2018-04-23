package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.impl.ServiceFactory;

public class NewProductLogic {
    private static final ProductService productService = ServiceFactory.newProductService();

    public static Product createProduct(String name, String description, Long count, Long price) {
        Product product = new Product(name, description, count, price);
        return productService.create(product);
    }
}
