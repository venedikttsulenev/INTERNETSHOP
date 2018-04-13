package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.PropertyValueException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private DAO<User> userDAO = new UserDAO();

    public User create(User user) {
        return userDAO.create(user);
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public List<User> getAll() {
        return userDAO.getAll();
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

    private boolean checkFieldsNULL(User user) {
        return (user.getLogin() == null || user.getPassword() == null);
    }
}
