package com.epam.internetshop.services.validator.impl;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

    public boolean validateAll(User user) {
        if (!validateLogin(user.getLogin()))
            return false;
        if (!validatePassword(user.getPassword()))
            return false;
        return true;
    }

    public boolean validateLogin(String login) {
        if (login == null)
            return false;
        if (!isSuitable(login))
            return false;
        if (!isRightFormat(login))
            return false;
        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null)
            return false;
        if (!isRightLength(password))
            return false;
        if (!isRightSymbols(password))
            return false;
        return true;
    }

    private boolean isSuitable(String string) {
        return true;
    }

    private boolean isRightFormat(String string) {
        return true;
    }

    private boolean isRightLength(String string) {
        return string.length() >= 8 && string.length() <= 30;
    }

    private boolean isRightSymbols(String string) {
        return true;
    }
}
