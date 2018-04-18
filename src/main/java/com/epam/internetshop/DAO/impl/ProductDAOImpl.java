package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.exception.ProductException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public Long getCount(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Product product = null;

        product = session.get(Product.class, id);

        session.close();
        return (product == null) ? null : product.getCount();
    }

    public Product decrementCount(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Product product = null;

        Transaction transaction = session.beginTransaction();
        try {
            product = session.get(Product.class, id);

            Long count = product.getCount();
            if (count == 0)
                throw new ProductException("Not enough product.");
            product.setCount(count - 1);
            session.update(product);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } catch (ProductException e) {
            transaction.rollback();
            throw new ProductException("Not enough product.");
        }

        session.close();
        return product;
    }

    public void decrementCount(List<ProductCount> productCountList) {
        Session session = HibernateSessionFactory.getSession();

        Transaction transaction = session.beginTransaction();
        try {
            for (ProductCount productCount : productCountList) {
                Product product = session.get(Product.class, productCount.getProductId());

                Long count = product.getCount(),
                        buyingCount = productCount.getCount();
                if (count < buyingCount)
                    throw new ProductException();
                product.setCount(count - buyingCount);

                session.update(product);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (ProductException e) {
            transaction.rollback();
            throw new ProductException("Not enough product available.");
        }

        session.close();
    }

    public List<Product> getAllSorted(String columnName, boolean isAsc) {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        if (isAsc)
            query.select(root).
                    orderBy(builder.asc(root.get(columnName)));
        else
            query.select(root).
                    orderBy(builder.desc(root.get(columnName)));

        List result = session.createQuery(query).getResultList();

        session.close();
        return result;
    }
}
