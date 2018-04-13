package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.User;

import java.util.List;

public interface DAO<T> {
    T create(T entity);
    List<T> getAll();
    T getById(Long id);
    T update(T entity);
    void delete(T entity);
}
