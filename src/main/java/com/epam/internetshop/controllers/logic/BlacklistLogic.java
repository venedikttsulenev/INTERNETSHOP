package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.ServiceFactory;

public class BlacklistLogic {
    private static final UserService userService = ServiceFactory.newUserService();

    public static boolean blackListUser(String userLogin) {
        User user = userService.setBlackListed(userLogin, true);
        return user != null && user.isBlackListed();
    }

    public static boolean unBlackListUser(String userLogin) {
        User user = userService.setBlackListed(userLogin, false);
        return user != null && !user.isBlackListed();
    }
}
