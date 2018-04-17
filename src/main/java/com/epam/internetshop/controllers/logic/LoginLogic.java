package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManager;

public class LoginLogic {

    public static boolean userExists(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        UserService service = ServiceManager.newUserService();
        User userByLogin = service.getByLogin(user);

        return (userByLogin != null);
    }
}
