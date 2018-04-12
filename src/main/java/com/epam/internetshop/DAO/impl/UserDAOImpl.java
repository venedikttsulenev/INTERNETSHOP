package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    public User createUser(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);

        transaction.commit();

        

        session.close();

        return null;
    }

    public List<User> getAllUsers() {
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
