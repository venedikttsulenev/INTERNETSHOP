package com.epam.internetshop.services;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();

    User getByLogin(String login);

    User getById(Long Id);
}
