package com.epam.internetshop.services;

import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.UserException;

import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();

    List<User> getAllUsers();

    User createUser(String login, String password);

    User getByLogin(String login);

    User getById(Long Id);

    boolean isEnoughCurrency(Long productId, Long currencyAmount) throws UserException;

    User increaseAccount(String login, Long currencyAmount);

    Boolean isAdmin(String login);

    Boolean isBlackListed(String login);
}
