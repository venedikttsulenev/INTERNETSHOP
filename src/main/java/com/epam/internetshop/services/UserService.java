package com.epam.internetshop.services;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();

    List<User> select(User user);

    User getById(Long Id);

    List<User> selectSort();

}
