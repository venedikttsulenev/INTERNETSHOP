package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.UserDAOImpl;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
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
        try {
            return userDAO.update(user);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public void delete(User user) {
        try {
            userDAO.delete(user);
        } catch (RuntimeException e) {
        }
    }

    public List<User> getAll() {
        try {
            return userDAO.getAll();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public User createUser(String login, String password) {
        if (login == null || password == null ||
                !userValidator.validateLogin(login) || !userValidator.validatePassword(password))
            return null;
        if (getByLogin(login) != null)
            return null;
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setBlackListed(false);
        user.setAdmin(false);
        try {
            return userDAO.create(user);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public User getByLogin(String login) {
        if (login == null || !userValidator.validateLogin(login))
            return null;
        try {
            return ((UserDAOImpl) userDAO).getByLogin(login);
        } catch (Throwable e) {
            return null;
        }
    }

    public User getById(Long Id) {
        User user = null;
        try {
            user = userDAO.getById(Id);
        } catch (RuntimeException e) {
        }
        return user;
    }
}
