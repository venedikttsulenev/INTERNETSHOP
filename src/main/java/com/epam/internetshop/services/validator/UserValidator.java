package com.epam.internetshop.services.validator;

import com.epam.internetshop.domain.User;

/**
 * Created by 1 on 14.04.2018.
 */
public interface UserValidator {
    boolean validateAll(User user);
    boolean validateLogin(String login);
    boolean validatePassword(String password);
}
