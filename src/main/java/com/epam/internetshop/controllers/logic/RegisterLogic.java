package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceManger;

public class RegisterLogic {

    public static boolean loginIsAlreadyTaken(String login) {
        User user = new User();
        user.setLogin(login);

        UserService userService = ServiceManger.newUserService();
        return (userService.getByLogin(user) != null);
    }

    public static User registerUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setAdmin(false);
        user.setBlackListed(false);

        UserService service = ServiceManger.newUserService();
        return service.create(user);
    }
}
