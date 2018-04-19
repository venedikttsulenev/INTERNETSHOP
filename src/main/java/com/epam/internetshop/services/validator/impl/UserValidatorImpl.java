package com.epam.internetshop.services.validator.impl;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.validator.UserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {

    private Pattern symbolsPattern = Pattern.compile("^[a-zA-Z0-9_.-]*$");

    public boolean validateAll(User user) {
        if (!validateLogin(user.getLogin()))
            return false;
        if (!validatePassword(user.getPassword()))
            return false;
        if (!validateAccount(user.getAccount()))
            return false;
        return true;
    }

    public boolean validateLogin(String login) {
        if (login == null)
            return false;
        if (!isRightLoginLength(login))
            return false;
        if (!isSuitable(login))
            return false;
        if (!isRightSymbols(login))
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

    public boolean validateAccount(Long account) {
        if (account == null)
            return false;
        if (!isRightAccountAmount(account))
            return false;
        return true;
    }

    private boolean isRightAccountAmount(Long account) {
        return account >= 0;
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

    private boolean isRightLoginLength(String string) {
        return string.length() >= 3 && string.length() <= 50;
    }

    private boolean isRightSymbols(String string) {
        Matcher matcher = symbolsPattern.matcher(string);
        return matcher.matches();
    }
}
