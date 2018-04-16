package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
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

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        criteria.from(Payment.class);
        List<Payment> list = session.createQuery(criteria).getResultList();

        transaction.commit();

        session.close();
        return list;
    }

    public List<Payment> createFromPaylist(User user, List<Product> productList) {
        Session session = HibernateSessionFactory.getSession();
        List<Payment> resultList = new ArrayList<Payment>();

        Transaction transaction = session.beginTransaction();

        try {
            for (Product product : productList) {
                Payment payment = new Payment();
                payment.setUserId(user);
                payment.setProductId(product);
                payment.setPrice(product.getPrice());
                payment.setPaydate(new Date());
                session.save(payment);
                resultList.add(payment);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException e1) {

            }
            resultList = null;
        }

        session.close();
        return resultList;
    }

    public Payment getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Payment payment = null;

        Transaction transaction = session.beginTransaction();
        payment = session.get(Payment.class, id);
        transaction.commit();

        session.close();
        return payment;
    }


}
