package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface UserDAO {
    User create(User entity);

    List<User> getAll();

    List<User> getAllUsers();

    User getById(Long id);

    User update(User entity);

    void delete(User entity);

    User getByLogin(String login);

    Boolean isBlackListed(String login);

    Boolean isAdmin(String login);

    Long getAccount(Long userId);

    void withdraw(Long userId, Long withdrawAmount);

    void incrementAccount(Long userId, Long amount);
}
