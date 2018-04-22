package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;
import com.epam.internetshop.services.validator.UserValidator;

public class LoginLogic {

    public static boolean login(String login, String password) {

        UserService service = ServiceFactory.newUserService();
        UserValidator userValidator = ServiceFactory.newUserValidator();

        if (!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            return false;
        }

        User userByLogin = service.getByLogin(login);

        return (userByLogin != null && !userByLogin.isBlackListed() && password.equals(userByLogin.getPassword()));
        /* TODO: Secure authentication */
    }
}
