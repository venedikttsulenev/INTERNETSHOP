package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;

public class RegisterLogic {

    public static boolean loginIsAlreadyTaken(String login) {
        UserService userService = ServiceFactory.newUserService();

        return (userService.getByLogin(login) != null);
    }

    public static User registerUser(String login, String password) {
        UserService service = ServiceFactory.newUserService();
        return service.createUser(login, password);
    }
}
