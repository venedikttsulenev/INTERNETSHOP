package com.epam.internetshop.controllers.logic;

import java.util.List;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.Service;
import com.epam.internetshop.services.UserService;

public class LoginLogic {

    public static boolean userExists(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        UserService userService = new Service();
        List<User> selectedUsers = userService.select(user);

        return (selectedUsers != null && selectedUsers.size() == 1);
    }
}
