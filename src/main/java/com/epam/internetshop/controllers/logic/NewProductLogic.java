package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.manager.ServiceFactory;

public class NewProductLogic {
    public static Product createProduct(String name, String description, Long count, Long price) {
        Product product = new Product(name, description, count, price);
        return ServiceFactory.newProductService().create(product);
    }
}
