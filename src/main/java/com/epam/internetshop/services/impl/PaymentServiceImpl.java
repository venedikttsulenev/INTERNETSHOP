package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.validator.PaymentValidator;
import com.epam.internetshop.services.validator.ProductValidator;
import com.epam.internetshop.services.validator.UserValidator;
import com.epam.internetshop.services.validator.impl.PaymentValidatorImpl;
import com.epam.internetshop.services.validator.impl.ProductValidatorImpl;
import com.epam.internetshop.services.validator.impl.UserValidatorImpl;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAOImpl();
    private ProductValidator productValidator = new ProductValidatorImpl();
    private UserValidator userValidator = new UserValidatorImpl();
    private PaymentValidator paymentValidator = new PaymentValidatorImpl();

    public Payment create(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        try {
            return paymentDAO.create(payment);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public Payment update(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        try {
            return paymentDAO.update(payment);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public void delete(Payment payment) {
        try {
            paymentDAO.delete(payment);
        } catch (RuntimeException e) {
        }
    }

    public List<Payment> getAll() {
        try {
            return paymentDAO.getAll();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public List<Payment> performPayment(User user, List<Product> productList) {
        if (user == null || productList == null) {
            return null;
        }
        for (Product product : productList) {
            if (product == null || !productValidator.validateAll(product))
                return null;
        }
        return paymentDAO.createFromPaylist(user, productList);
    }

    public Payment getById(Long Id) {
        try {
            return paymentDAO.getById(Id);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
