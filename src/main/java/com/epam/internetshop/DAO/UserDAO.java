package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
