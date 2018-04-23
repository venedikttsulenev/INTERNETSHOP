package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;
import com.epam.internetshop.services.validator.UserValidator;

public class LoginLogic {
    private static final UserService userService = ServiceFactory.newUserService();
    private static final UserValidator userValidator = ServiceFactory.newUserValidator();

    public static User login(String login, String password) {
        if (!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            return null;
        }

        User userByLogin = userService.getByLogin(login);

        if (userByLogin == null || !password.equals(userByLogin.getPassword()))
            return null;
        else
            return userByLogin;
    }
}
