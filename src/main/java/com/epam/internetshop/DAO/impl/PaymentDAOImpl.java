package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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

    public void createFromPaylist(User user, List<ProductCount> productList) throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (ProductCount productCount : productList) {
                Payment payment = new Payment(user, productCount.getProduct(),
                        productCount.getProductPrice(), new Date(), productCount.getCount());
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

    public Payment getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Payment payment;

        payment = session.get(Payment.class, id);

        session.close();
        return payment;
    }
}
