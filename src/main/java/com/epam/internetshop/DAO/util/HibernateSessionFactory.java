package com.epam.internetshop.DAO.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            sessionFactory = config.configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object."
                    + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}
