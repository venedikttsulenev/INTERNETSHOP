package com.epam.internetshop.DAO.impl;

import com.epam.internetshop.DAO.DAOFactory;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.DAO.impl.ProductDAOImpl;
import com.epam.internetshop.DAO.impl.UserDAOImpl;

public class HibernateDAOFactory implements DAOFactory {
    @Override
    public UserDAO newUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public ProductDAO newProductDAO() {
        return new ProductDAOImpl();
    }

    @Override
    public PaymentDAO newPaymentDAO() {
        return new PaymentDAOImpl();
    }
}
