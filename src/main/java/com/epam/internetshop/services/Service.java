package com.epam.internetshop.services;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.User;

import java.util.List;

public class Service implements UserService{
    private DAO userDAO = new UserDAO();

    public User create(User user) {
        return (User)userDAO.create(user);
    }

    public User update(User user) {
        return null;
    }

    public User delete(User user) {
        return null;
    }

    public List<User> select(User user) {
        return null;
    }

    public User selectById(User user) {
        return null;
    }

    public List<User> selectSort() {
        return null;
    }
}
