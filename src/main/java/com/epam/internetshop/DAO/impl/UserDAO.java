package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.domain.User;
import org.hibernate.*;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import javax.persistence.criteria.*;

import java.util.List;

public class UserDAO implements DAO<User> {

    public List<User> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        session.beginTransaction();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);

        List<User> list = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();

        session.close();
        return list;
    }

    public User getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        User user = null;

        session.beginTransaction();
        user = session.get(User.class, id);
        session.getTransaction().commit();

        session.close();
        return user;
    }

}
