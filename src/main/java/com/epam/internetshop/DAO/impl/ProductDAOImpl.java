package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends DAO<Product> implements ProductDAO {

    public List<Product> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        criteria.from(Product.class);
        List<Product> list = session.createQuery(criteria).getResultList();

        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Product product = null;

        product = session.get(Product.class, id);

        session.close();
        return product;
    }

    public Product decrementCount(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Product product = null;

        Transaction transaction = session.beginTransaction();
        try {
            product = session.get(Product.class, id);

            Long count = product.getCount();
            if (count == 0)
                throw new RuntimeException();
            product.setCount(count - 1);
            session.update(product);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
        }

        session.close();
        return product;
    }

    public List<Product> decrementCount(List<Long> id) {
        Session session = HibernateSessionFactory.getSession();
        List<Product> productList = new ArrayList<Product>();

        Transaction transaction = session.beginTransaction();
        try {
            for (Long id1 : id) {
                Product product = session.get(Product.class, id1);

                Long count = product.getCount();
                if (count == 0)
                    throw new RuntimeException();
                product.setCount(count - 1);
                session.update(product);
                productList.add(product);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            productList=null;
        }

        session.close();
        return productList;
    }

}
