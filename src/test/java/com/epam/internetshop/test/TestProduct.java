package com.epam.internetshop.test;

import com.epam.internetshop.domain.Product;
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
                new Product("productName", "disc", 10L, 10L)));
    }

    @Test
    public void createZeroCount() {
        assertNotEquals(null, productService.create(
                new Product("productName", "disc", 0L, 10L)));
    }

    @Test
    public void createZeroPrice() {
        assertNotEquals(null, productService.create(
                new Product("productName", "disc", 1L, 0L)));
    }

    @Test
    public void update() {
        Product product =
                productService.create(new Product("productName", "disc", 1L, 0L));
        product.setName("eman");
        assertNotEquals(null, productService.update(product));
    }

    @Test
    public void updateNullName() {
        Product product =
                productService.create(new Product("productName", "disc", 1L, 0L));
        product.setName(null);
        assertEquals(null, productService.update(product));
    }

    @Test
    public void delete() {
        Product product =
                productService.create(new Product("productName", "disc", 1L, 0L));
        productService.delete(product);
    }

    @Test
    public void createWrongCount() {
        assertEquals(null, productService.create(
                new Product("productName", "disc", -10L, 10L)));
    }

    @Test
    public void createWrongPrice() {
        assertEquals(null, productService.create(
                new Product("productName", "disc", 10L, -10L)));
    }
}
