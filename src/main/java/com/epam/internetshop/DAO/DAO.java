package com.epam.internetshop.DAO;

import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DAO<T> {

    private final static Logger logger = Logger.getLogger(DAO.class);

    public T create(T entity) {
        Session session = HibernateSessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();

        session.close();
        logger.info("Entity created");
        return entity;
    }

    public T update(T entity) {
        Session session = HibernateSessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();

        session.close();
        logger.info("Entity updated");
        return entity;
    }

    public void delete(T entity) {
        Session session = HibernateSessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();

        session.close();
        logger.info("Entity deleted");
    }
}
