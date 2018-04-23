package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;
import com.epam.internetshop.services.validator.UserValidator;

public class LoginLogic {

    public static User login(String login, String password) {

        UserService service = ServiceFactory.newUserService();
        UserValidator userValidator = ServiceFactory.newUserValidator();

        if (!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            return null;
        }

        User userByLogin = service.getByLogin(login);

        if (userByLogin == null || !password.equals(userByLogin.getPassword()))
            return null;
        else
            return userByLogin;
        /* TODO: Secure authentication */
    }
}
