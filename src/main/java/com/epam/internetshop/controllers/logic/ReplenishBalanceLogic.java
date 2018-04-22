package com.epam.internetshop.controllers.logic;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.manager.ServiceFactory;

public class ReplenishBalanceLogic {
    public static User replenishBalance(User user, Long amount) {
        return ServiceFactory.newUserService().increaseAccount(user.getLogin(), amount);
    }
}
