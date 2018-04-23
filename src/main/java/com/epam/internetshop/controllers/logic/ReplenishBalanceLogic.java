package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.manager.ServiceFactory;

public class ReplenishBalanceLogic {
    private static final UserService userService = ServiceFactory.newUserService();

    public static User replenishBalance(User user, Long amount) {
        return userService.increaseAccount(user.getLogin(), amount);
    }
}
