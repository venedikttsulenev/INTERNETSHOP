package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManager;

public class LoginLogic {

    public static boolean login(String login, String password) {
        UserService service = ServiceManager.newUserService();
        User userByLogin = service.getByLogin(login);

        return (userByLogin != null && password.equals(userByLogin.getPassword()));
        /* TODO: Secure authentication */
    }
}
