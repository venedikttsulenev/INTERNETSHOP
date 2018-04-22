package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.manager.ServiceFactory;

public class AddToCartLogic {
    public static Product getProductById(Long id) {
        return ServiceFactory.newProductService().getById(id);
    }
}
