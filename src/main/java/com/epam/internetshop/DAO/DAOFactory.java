package com.epam.internetshop.DAO;

public interface DAOFactory {
    UserDAO newUserDAO();
    ProductDAO newProductDAO();
    PaymentDAO newPaymentDAO();
}
