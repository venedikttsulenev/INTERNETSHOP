package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManager;
import com.epam.internetshop.services.validator.UserValidator;

public class LoginLogic {



    public static boolean login(String login, String password) {

        UserService service = ServiceManager.newUserService();
        UserValidator userValidator = ServiceManager.newUserValidator();

        if (!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            return false;
        }

        User userByLogin = service.getByLogin(login);

        return (userByLogin != null && password.equals(userByLogin.getPassword()));
        /* TODO: Secure authentication */
    }
}
