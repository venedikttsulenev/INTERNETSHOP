package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.Service;
import com.epam.internetshop.services.UserService;

import java.util.List;

public class RegisterLogic {

    public static boolean loginIsAlreadyTaken(String login) {
        User user = new User();
        user.setLogin(login);

        UserService service = new Service();
        List<User> usersByThisLogin = service.select(user);
        return (usersByThisLogin != null);
    }

    public static User registerUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setAdmin(false);
        user.setBlackListed(false);

        UserService service = new Service();
        return service.create(user);
    }
}
