package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;

public class BlacklistLogic {
    private static final UserService userService = ServiceFactory.newUserService();

    public static boolean blackListUser(String userLogin) {
        User user = userService.getByLogin(userLogin);
        user.setBlackListed(true);
        user = userService.update(user);
        return user.isBlackListed();
    }

    public static boolean unBlackListUser(String userLogin) {
        User user = userService.getByLogin(userLogin);
        user.setBlackListed(false);
        user = userService.update(user);
        return !user.isBlackListed();
    }
}
