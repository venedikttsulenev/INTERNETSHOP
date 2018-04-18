package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.UserDAOImpl;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.exception.UserException;
import com.epam.internetshop.services.validator.UserValidator;
import com.epam.internetshop.services.validator.impl.UserValidatorImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    private UserValidator userValidator = new UserValidatorImpl();

    public User create(User user) {
        if (user == null || !userValidator.validateAll(user))
            return null;
        return userDAO.create(user);
    }

    public User update(User user) {
        if (user == null || !userValidator.validateAll(user))
            return null;
        return userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User createUser(String login, String password) {
        if (login == null || password == null ||
                !userValidator.validateLogin(login) || !userValidator.validatePassword(password))
            return null;
        if (getByLogin(login) != null)
            return null;
        User user = new User(login, password);
        return userDAO.create(user);
    }

    public User getByLogin(String login) {
        if (login == null || !userValidator.validateLogin(login))
            return null;
        return userDAO.getByLogin(login);
    }

    public User getById(Long Id) {
        if (Id == null)
            return null;
        return userDAO.getById(Id);
    }

    public boolean isEnoughCurrency(Long userId, Long currencyAmount) {
        if (userId == null || currencyAmount == null)
            throw new UserException("Null values.");
        if (currencyAmount < 0)
            throw new UserException("Can't withdraw less than zero.");
        return userDAO.getAccount(userId) >= currencyAmount;
    }

    public void increaseAccount(String login, Long currencyAmount) {
        if (login == null || currencyAmount == null)
            throw new UserException("Null values.");
        if (currencyAmount <= 0)
            throw new UserException("Can't increase on zero or less.");
        User user = userDAO.getByLogin(login);
        userDAO.incrementAccount(user.getId(), currencyAmount);
    }

    public Boolean isAdmin(String login) {
        return userDAO.isAdmin(login);
    }

    public Boolean isBlackListed(String login) {
        return userDAO.isBlackListed(login);
    }
}
