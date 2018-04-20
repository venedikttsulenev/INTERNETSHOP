package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

public class PaymentDAOImpl extends DAO<Payment> implements PaymentDAO {

    public List<Payment> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        criteria.from(Payment.class);
        List<Payment> list = session.createQuery(criteria).getResultList();

        session.close();
        return list;
    }

    public void createFromPaylist(Long userId, List<ProductCount> productCountList) throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (ProductCount productCount : productCountList) {
                Product product = session.get(Product.class, productCount.getProductId());
                User user = session.get(User.class, userId);
                Payment payment = new Payment(user, product,
                        product.getPrice(), productCount.getCount(), new Date());
                session.save(payment);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw new HibernateException("Can't create payments.");
        }
        session.close();
    }

    public void performPayOperation(Long userId, List<ProductCount> productCountList, Long withdrawAmount) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (ProductCount productCount : productCountList) {
                Product product = session.get(Product.class, productCount.getProductId());
                Long count = product.getCount();
                Long buyingCount = productCount.getCount();

                if (count < buyingCount)
                    throw new ProductException();
                product.setCount(count - buyingCount);
                session.update(product);
            }

            User user = session.get(User.class, userId);
            Long account = user.getAccount();
            if (account < withdrawAmount)
                throw new UserException();
            user.setAccount(account - withdrawAmount);
            session.update(user);

            for (ProductCount productCount : productCountList) {
                Product product = session.get(Product.class, productCount.getProductId());
                Payment payment = new Payment(user, product,
                        product.getPrice(), productCount.getCount(), new Date());
                session.save(payment);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw new HibernateException("Can't perform operation.");
        } catch (UserException e) {
            transaction.rollback();
            throw new UserException("Not enough cash.");
        } catch (ProductException e) {
            transaction.rollback();
            throw new ProductException("Not enough product available.");
        }
        session.close();
    }

    public Payment getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Payment payment;

        payment = session.get(Payment.class, id);

        session.close();
        return payment;
    }
}
