package com.epam.internetshop.DAO;

import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DAO<T> {
    public T create(T entity) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();

        session.close();
        return entity;
    }

    public T update(T entity) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();

        session.close();
        return entity;
    }

    public void delete(T entity) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();

        session.close();
    }
}
