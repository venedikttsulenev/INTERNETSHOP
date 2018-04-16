package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.DAO.impl.ProductDAOImpl;
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
    private ProductDAO productDAO = new ProductDAOImpl();
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

    public List<Payment> performPayment(User user, List<Long> productIdList) {
        if (user == null || productIdList == null) {
            return null;
        }
        for (Long id : productIdList) {
            if (id == null)
                return null;
        }

        List<Product> productList = productDAO.decrementCount(productIdList);
        if (productIdList == null) return null;

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
