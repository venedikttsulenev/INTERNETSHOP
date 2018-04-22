package com.epam.internetshop.test;


import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.manager.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestProduct {
    private ProductService productService = ServiceFactory.newProductService();

    @Test
    public void createProduct() {
        assertNotEquals(null, productService.create(
                new Product("name","disc",10L,10L)));
    }

    @Test
    public void createWrongCount() {
        assertEquals(null, productService.create(
                new Product("name","disc",-10L,10L)));
    }

    @Test
    public void createWrongPrice() {
        assertEquals(null, productService.create(
                new Product("name","disc",10L,-10L)));
    }
}
