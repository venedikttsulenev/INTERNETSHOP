package com.epam.internetshop.test;


import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestUser {
    private UserService userService = ServiceFactory.newUserService();

    @Test
    public void createAdmin() {
        assertNotEquals(null, userService.create(
                new User("TestAdmin", "12345678", 0L, false, true)));
    }

    @Test
    public void createWrongLogin() {
        assertEquals(null, userService.create(
                new User("ad", "12345678", 0L, false, false)));
    }

    @Test
    public void createWrongPassword() {
        assertEquals(null, userService.create(
                new User("TestPassadmin", "123456", 0L, false, false)));
    }

    @Test
    public void createWrongAccount() {
        assertEquals(null, userService.create(
                new User("TestAccAdmin", "12345678", -1L, false, false)));
    }
}
