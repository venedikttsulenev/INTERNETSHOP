package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.UserException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class UserDAOImpl extends DAO<User> implements UserDAO {

    final static Logger logger = Logger.getLogger(PaymentDAOImpl.class);

    public List<User> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> list = session.createQuery(criteria).getResultList();

        session.close();
        return list;
    }

    public List<User> getAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("isAdmin"), Boolean.FALSE));

        List<User> list = session.createQuery(query).getResultList();

        session.close();
        return list;
    }

    public User getById(Long id) {
        Session session = HibernateSessionFactory.getSession();

        User user = session.get(User.class, id);

        session.close();
        return user;
    }

    public User getByLogin(String login) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        User user = null;

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("login"), login));

        List result = session.createQuery(query).getResultList();
        if (!result.isEmpty()) {
            user = (User) result.get(0);
        }

        session.close();
        return user;
    }

    public Boolean isBlackListed(String login) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Boolean isBlackListed = null;

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("login"), login));

        List result = session.createQuery(query).getResultList();
        if (!result.isEmpty()) {
            isBlackListed = ((User) result.get(0)).isBlackListed();
        }

        session.close();
        return isBlackListed;
    }

    public Boolean isAdmin(String login) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Boolean isAdmin = null;

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).
                where(builder.equal(root.get("login"), login));

        List result = session.createQuery(query).getResultList();
        if (!result.isEmpty()) {
            isAdmin = ((User) result.get(0)).isAdmin();
        }

        session.close();
        return isAdmin;
    }

    public Long getAccount(Long userId) {
        Session session = HibernateSessionFactory.getSession();

        User user = session.get(User.class, userId);

        session.close();
        return (user == null) ? null : user.getAccount();
    }

    public void withdraw(Long userId, Long withdrawAmount) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            Long account = user.getAccount();

            if (account < withdrawAmount) {
                logger.error("Not enough cash.");
                throw new UserException();
            }

            user.setAccount(account - withdrawAmount);
            session.update(user);
            transaction.commit();
            logger.info("Withdraw performed.");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            logger.error("Can't perform withdraw.");
            throw new UserException("Can't perform withdraw.");
        } catch (UserException e) {
            transaction.rollback();
            logger.error("Not enough money.");
            throw new UserException("Not enough cash.");
        }
        session.close();
    }

    public void incrementAccount(Long userId, Long amount) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        try {
            if (amount <= 0) throw new UserException();

            User user = session.get(User.class, userId);
            Long account = user.getAccount();
            user.setAccount(account + amount);
            session.update(user);

            transaction.commit();
            logger.info("Increment performed.");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            logger.error("Can't increment account.");
            throw new UserException("Can't increment account.");
        } catch (UserException e) {
            transaction.rollback();
            logger.error("Can't increase on zero or less.");
            throw new UserException("Can't increase on zero or less.");
        }
        session.close();
    }
}
