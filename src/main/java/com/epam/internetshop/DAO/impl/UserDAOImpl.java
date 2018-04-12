package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    public User createUser(User user) {
        try {
            Session session = HibernateSessionFactory.getSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        return null;
    }

    public User getUserById(Long id) {
        return null;
    }

    public User updateUser(Long id, User user) {
        return null;
    }

    public void deleteUser(Long id) {

    }
}
