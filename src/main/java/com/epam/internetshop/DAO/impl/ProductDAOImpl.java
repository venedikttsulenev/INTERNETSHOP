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
import java.util.List;

public class ProductDAOImpl extends DAO<Product> implements ProductDAO {

    public List<Product> getAll() {
        Session session = HibernateSessionFactory.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        Transaction transaction = session.beginTransaction();

        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        criteria.from(Product.class);
        List<Product> list = session.createQuery(criteria).getResultList();

        transaction.commit();

        session.close();
        return list;
    }

    public Product getById(Long id) {
        Session session = HibernateSessionFactory.getSession();
        Product product;

        Transaction transaction = session.beginTransaction();
        product = session.get(Product.class, id);
        transaction.commit();

        session.close();
        return product;
    }

}
