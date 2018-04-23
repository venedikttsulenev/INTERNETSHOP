package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;

public class RegisterLogic {
    private static final UserService userService = ServiceFactory.newUserService();

    public static boolean loginIsAlreadyTaken(String login) {
        return (userService.getByLogin(login) != null);
    }

    public static User registerUser(String login, String password) {
        return userService.createUser(login, password);
    }
}
