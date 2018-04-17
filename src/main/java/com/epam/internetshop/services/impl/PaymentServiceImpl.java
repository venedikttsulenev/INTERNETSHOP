package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.DAO.impl.ProductDAOImpl;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.exception.PaymentException;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.validator.PaymentValidator;
import com.epam.internetshop.services.validator.ProductValidator;
import com.epam.internetshop.services.validator.UserValidator;
import com.epam.internetshop.services.validator.impl.PaymentValidatorImpl;
import com.epam.internetshop.services.validator.impl.ProductValidatorImpl;
import com.epam.internetshop.services.validator.impl.UserValidatorImpl;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductValidator productValidator = new ProductValidatorImpl();
    private UserValidator userValidator = new UserValidatorImpl();
    private PaymentValidator paymentValidator = new PaymentValidatorImpl();

    public Payment create(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        return paymentDAO.create(payment);
    }

    public Payment update(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        return paymentDAO.update(payment);
    }

    public void delete(Payment payment) {
        paymentDAO.delete(payment);
    }

    public List<Payment> getAll() {
        return paymentDAO.getAll();
    }

    public void performPayment(User user, List<ProductCount> productCountList)
            throws ProductException, PaymentException, NullPointerException {

        if (user == null || productCountList == null) {
            throw new NullPointerException();
        }
        for (ProductCount productCount : productCountList) {
            if (productCount == null)
                throw new NullPointerException();
            if (productCount.getCount() < 1 ||
                    productDAO.getCount(productCount.getProductId()) < productCount.getCount())
                throw new ProductException("Not enough products.");
        }

        productDAO.decrementCount(productCountList);
        if (productCountList == null)
            throw new ProductException();

        paymentDAO.createFromPaylist(user, productCountList);
    }

    public Payment getById(Long Id) {
        return paymentDAO.getById(Id);
    }
}
