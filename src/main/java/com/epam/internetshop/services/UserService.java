package com.epam.internetshop.services;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();

    User login(User user);

    User getByLogin(User user);

    @Deprecated
    List<User> select(User user);

    User getById(Long Id);

    @Deprecated
    List<User> selectSort();
}
