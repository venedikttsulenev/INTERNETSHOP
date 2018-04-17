package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManager;

public class RegisterLogic {

    public static boolean loginIsAlreadyTaken(String login) {
        User user = new User();
        user.setLogin(login);

        UserService userService = ServiceManager.newUserService();
        return (userService.getByLogin(user) != null);
    }

    public static User registerUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setAdmin(false);
        user.setBlackListed(false);

        UserService service = ServiceManager.newUserService();
        return service.create(user);
    }
}
