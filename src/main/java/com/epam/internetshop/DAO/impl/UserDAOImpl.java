package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.UserException;
import org.hibernate.*;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class UserDAOImpl extends DAO<User> implements UserDAO {

    public List<User> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> list = session.createQuery(criteria).getResultList();

        session.close();
        return list;
    }

    public User getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        User user = null;

        user = session.get(User.class, id);

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
        if (!result.isEmpty())
            user = (User) result.get(0);

        session.close();
        return user;
    }

    public Long getAccount(Long userId) {
        Session session = HibernateSessionFactory.getSession();
        User user = null;

        user = session.get(User.class, userId);

        session.close();
        return (user == null) ? null : user.getAccount();
    }

    public void withdraw(Long userId, List<ProductCount> productCountList) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            Long account = user.getAccount(),
                    currencyAmount = 0L;

            for (ProductCount productCount : productCountList) {
                Product product = session.get(Product.class, productCount.getProductId());
                Long price = product.getPrice(),
                        buyingCount = productCount.getCount();
                currencyAmount += buyingCount * price;
            }
            if (account < currencyAmount)
                throw new UserException("Not enough cash.");
            user.setAccount(account - currencyAmount);
            session.update(user);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            throw new UserException("Can't perform withdraw.");
        } catch (UserException e) {
            transaction.rollback();
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
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            throw new UserException("Can't increment account.");
        } catch (UserException e) {
            transaction.rollback();
            throw new UserException("Can't increase on zero or less.");
        }
        session.close();
    }
}
