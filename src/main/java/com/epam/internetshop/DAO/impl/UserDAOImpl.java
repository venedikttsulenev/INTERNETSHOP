package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.domain.User;
import org.hibernate.*;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class UserDAOImpl extends DAO<User> implements UserDAO {

    public List<User> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);

        List<User> list = session.createQuery(criteria).getResultList();

        transaction.commit();

        session.close();
        return list;
    }

    public User getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        User user = null;

        Transaction transaction = session.beginTransaction();
        user = session.get(User.class, id);
        transaction.commit();

        session.close();
        return user;
    }

    public User getByLogin(String login) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        User user = null;

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("login"), login));
        user = (User) session.createQuery(query).getSingleResult();

        transaction.commit();

        session.close();
        return user;
    }

    public User getByLoginAndPassword(User user) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("login"), user.getLogin()),
                        builder.equal(root.get("password"), user.getPassword()));
        user = (User) session.createQuery(query).getSingleResult();

        transaction.commit();

        session.close();
        return user;
    }
}
