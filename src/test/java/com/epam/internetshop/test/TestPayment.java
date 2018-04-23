package com.epam.internetshop.test;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;
import com.epam.internetshop.services.manager.ServiceFactory;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPayment {
    private PaymentService paymentService = ServiceFactory.newPaymentService();
    private ProductService productService = ServiceFactory.newProductService();
    private UserService userService = ServiceFactory.newUserService();

    @Test
    public void create() {
        Product product = productService.create(new Product("testing", null, 10L, 10L));
        User user = userService.createUser("TestPayment", "12345678");
        assertNotEquals(null,
                paymentService.create(new Payment(user, product, 10L, 10L, new Date())));
    }

    @Test
    public void createWrongUser() {
        Product product = productService.create(new Product("test", "prod", 10L, 10L));
        User user = null;
        assertEquals(null,
                paymentService.create(new Payment(user, product, 10L, 10L, new Date())));
    }

    @Test
    public void createWrongProduct() {
        Product product = null;
        User user = userService.createUser("PaymentAdmin2", "12345678");
        assertEquals(null,
                paymentService.create(new Payment(user, product, 10L, 10L, new Date())));
    }

    @Test(expected = UserException.class)
    public void testPaymentNotEnoughCash() {
        User user = userService.create(new User("paymentTest", "12345678", 0L, false, false));
        Product product = productService.create(
                new Product("name", "disc", 10L, 10L));
        HashMap<Product, Long> map = new HashMap<>();
        map.put(product, 1L);
        paymentService.performPayment(user.getLogin(), map);
    }

    @Test
    public void testPayment() {
        User user = userService.create(new User("paymentTest1", "12345678", 10L, false, false));
        Product product = productService.create(
                new Product("name", "disc", 10L, 10L));
        HashMap<Product, Long> map = new HashMap<>();
        map.put(product, 1L);
        paymentService.performPayment(user.getLogin(), map);
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentNullLogin() {
        Product product = productService.create(
                new Product("name", "disc", 10L, 10L));
        HashMap<Product, Long> map = new HashMap<>();
        map.put(product, 1L);
        paymentService.performPayment(null, map);
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentNullMap() {
        User user = userService.create(new User("paymentTest2", "12345678", 10L, false, false));
        paymentService.performPayment(user.getLogin(), null);
    }

    @Test(expected = ProductException.class)
    public void testPaymentNotEnoughProduct() {
        User user = userService.create(
                new User("paymentTest3", "12345678", 10L, false, false));
        Product product = productService.create(
                new Product("name", "disc", 1L, 10L));
        HashMap<Product, Long> map = new HashMap<>();
        map.put(product, 2L);
        paymentService.performPayment(user.getLogin(), map);
    }
}
