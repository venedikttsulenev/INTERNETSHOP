package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.exception.ProductException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

public class ProductDAOImpl extends DAO<Product> implements ProductDAO {

    final static Logger logger = Logger.getLogger(ProductDAOImpl.class);

    public List<Product> getAll() {
        Session session = HibernateSessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        criteria.from(Product.class);
        List<Product> list = session.createQuery(criteria).getResultList();

        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = HibernateSessionFactory.openSession();

        Product product = session.get(Product.class, id);

        session.close();
        return product;
    }

    public Long getCount(Long id) {
        Session session = HibernateSessionFactory.openSession();

        Product product = session.get(Product.class, id);

        session.close();
        return (product == null) ? null : product.getCount();
    }

    public Product setCount(Long productId, Long productCount) {
        if (productCount < 0) {
            logger.error("Can't set count.");
            throw new ProductException();
        }
        Session session = HibernateSessionFactory.openSession();
        Product product = session.get(Product.class, productId);
        product.setCount(productCount);
        Transaction transaction = session.beginTransaction();

        session.update(product);
        transaction.commit();

        session.close();
        return product;
    }

    public void increaseCount(Long productId, Long additionalCount) {
        if (additionalCount < 1) {
            logger.error("Can't increase.");
            throw new ProductException();
        }

        Session session = HibernateSessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, productId);
        Long count = product.getCount();

        product.setCount(count + additionalCount);

        session.update(product);
        transaction.commit();
        logger.info("Count increased.");

        session.close();
    }

    public void decrementCount(HashMap<Product, Long> productCountList) {
        Session session = HibernateSessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            logger.info("Count decrease began.");
            for (HashMap.Entry<Product, Long> entry : productCountList.entrySet()) {
                Long productId = entry.getKey().getId();
                Long productQuantity = entry.getValue();
                Product product = session.get(Product.class, productId);
                Long count = product.getCount();

                if (count < productQuantity || productQuantity < 1) {
                    logger.error("Can't perform decrement.");
                    throw new ProductException();
                }
                product.setCount(count - productQuantity);

                session.update(product);
                logger.info("Count decreased.");
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            logger.error("Can't perform decrement.");
            throw new ProductException("Can't perform decrement.");
        } catch (ProductException e) {
            transaction.rollback();
            logger.error("Not enough product available.");
            throw new ProductException("Not enough product available.");
        }
        session.close();
    }

    public void decrementCount(Long productId, Long decrementCount) {
        Session session = HibernateSessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, productId);
        Long count = product.getCount();

        if (decrementCount < 1 || count < decrementCount) {
            logger.error("Not enough product available.");
            throw new ProductException();
        }
        product.setCount(count - decrementCount);

        session.update(product);
        transaction.commit();
        session.close();
        logger.info("Count decreased.");
    }

    public List<Product> getAllSorted(String columnName, boolean isAsc) {
        Session session = HibernateSessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        if (isAsc) {
            query.select(root).
                    orderBy(builder.asc(root.get(columnName)));
        } else {
            query.select(root).
                    orderBy(builder.desc(root.get(columnName)));
        }

        List result = session.createQuery(query).getResultList();

        session.close();
        return result;
    }
}
