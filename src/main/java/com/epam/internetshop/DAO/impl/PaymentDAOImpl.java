package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.HashMap;
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

    public List<Payment> getPage(int pageSize, int page) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
        criteria.from(Payment.class);
        List<Payment> list = session.createQuery(criteria)
                .setFirstResult(pageSize * (page - 1))
                .setMaxResults(pageSize)
                .getResultList();

        session.close();
        return list;
    }

    public void createFromPaylist(Long userId, HashMap<Long, Long> productCountList) throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userId);
        try {
            for (HashMap.Entry<Long, Long> entry : productCountList.entrySet()) {
                Long productId = entry.getKey();
                Long productQuantity = entry.getValue();

                Product product = session.get(Product.class, productId);
                Payment payment = new Payment(user, product,
                        product.getPrice(), productQuantity, new Date());
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

    public void performPurchase(Long userId, HashMap<Long, Long> productCountList, Long withdrawAmount) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = session.get(User.class, userId);
            Long account = user.getAccount();
            if (account < withdrawAmount || withdrawAmount < 0)
                throw new UserException();
            user.setAccount(account - withdrawAmount);
            session.update(user);

            for (HashMap.Entry<Long, Long> entry : productCountList.entrySet()) {
                Long productId = entry.getKey();
                Long productQuantity = entry.getValue();
                Product product = session.get(Product.class, productId);
                Long count = product.getCount();

                if (productQuantity < 1)
                    throw new ProductException("Not enough products in cart.");
                if (count < productQuantity)
                    throw new ProductException("Not enough products available.");

                product.setCount(count - productQuantity);
                session.update(product);

                Payment payment = new Payment(user, product,
                        product.getPrice(), productQuantity, new Date());
                session.save(payment);
            }
            transaction.commit();
        } catch (HibernateException | UserException | ProductException e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw e;
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
