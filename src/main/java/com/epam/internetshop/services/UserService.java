package com.epam.internetshop.services;

import com.epam.internetshop.domain.User;

import java.util.List;

/**
 * Created by 1 on 13.04.2018.
 */
public interface UserService {
    User create(User user);
    User update(User user);
    User delete(User user);
    List<User> select(User user);
    User selectById(User user);
    List<User> selectSort();

}
