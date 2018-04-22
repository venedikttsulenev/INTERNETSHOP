package com.epam.internetshop.test;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.PaymentServiceImpl;
import com.epam.internetshop.services.manager.ServiceFactory;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPayment {
    private PaymentService paymentService = ServiceFactory.newPaymentService();
    private ProductService productService = ServiceFactory.newProductService();
    private UserService userService = ServiceFactory.newUserService();

    @Test
    public void create() {
        Product product = productService.create(new Product("testing", null, 10L, 10L));
        User user = userService.createUser("TestPayment","12345678");
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
        User user = userService.createUser("PaymentAdmin2","12345678");
        assertEquals(null,
                paymentService.create(new Payment(user, product, 10L, 10L, new Date())));
    }
}
