package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.validator.UserValidator;
import com.epam.internetshop.services.validator.impl.UserValidatorImpl;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.PropertyValueException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private DAO<User> userDAO = new UserDAO();
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

    public User login(User user) {
        if (user == null ||
                !userValidator.validateLogin(user.getLogin()) ||
                !userValidator.validatePassword(user.getPassword()) ||
                user.getPassword().isEmpty() || user.getLogin().isEmpty())
            return null;
        try {
            return ((UserDAO) userDAO).getByLoginAndPassword(user);
        } catch (Throwable e) {
            return null;
        }
    }

    public User getByLogin(User user) {
        if (user == null ||
                !userValidator.validateLogin(user.getLogin()) ||
                user.getLogin().isEmpty())
            return null;
        try {
            return ((UserDAO) userDAO).getByLogin(user);
        } catch (Throwable e) {
            return null;
        }
    }

    public List<User> select(User user) {
        return null;
    }

    public User getById(Long Id) {
        User user = null;
        try {
            user = userDAO.getById(Id);
        } catch (PropertyValueException e) {
            System.out.println("Wrong value.");
        } catch (PropertyNotFoundException e) {
            System.out.println("Not Fount Property.");
        }
        return user;
    }

    public List<User> selectSort() {
        return null;
    }
}
