package com.epam.internetshop.DAO;

import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.List;

public interface DAO<T> {
    default T create(T entity) {
        Session session = HibernateSessionFactory.getSession();

        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();

        session.close();
        return entity;
    }

    List<T> getAll();

    T getById(Long id);

    default T update(T entity) {
        Session session = HibernateSessionFactory.getSession();

        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();

        session.close();
        return entity;
    }

    default void delete(T entity) {
        Session session = HibernateSessionFactory.getSession();

        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();

        session.close();
    }
}
