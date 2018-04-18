package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManager;

public class RegisterLogic {

    public static boolean loginIsAlreadyTaken(String login) {
        UserService userService = ServiceManager.newUserService();

        return (userService.getByLogin(login) != null);
    }

    public static User registerUser(String login, String password) {
        User user = new User(login, password, false, false);

        UserService service = ServiceManager.newUserService();
        return service.create(user);
    }
}
