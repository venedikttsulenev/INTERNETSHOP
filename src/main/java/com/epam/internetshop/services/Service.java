package com.epam.internetshop.services;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.User;

import java.util.List;

public class Service implements UserService{
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

    public List<User> select(User user) {
        return null;
    }

    public User getById(Long Id) {
        return userDAO.getById(Id);
    }

    public List<User> selectSort() {
        return null;
    }
}
