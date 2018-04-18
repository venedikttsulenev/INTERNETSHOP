package com.epam.internetshop.services.validator;

import com.epam.internetshop.domain.User;

public interface UserValidator {
    boolean validateAll(User user);
    boolean validateLogin(String login);
    boolean validatePassword(String password);
    boolean validateAccount(Long account);
}
