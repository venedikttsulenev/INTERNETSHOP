package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.DAO.impl.ProductDAOImpl;
import com.epam.internetshop.DAO.impl.UserDAOImpl;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.exception.PaymentException;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;
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
    private UserDAO userDAO = new UserDAOImpl();
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

    public void performPayment(String userLogin, List<ProductCount> productCountList)
            throws ProductException, PaymentException {
        Long currencyAmount = 0L;
        User user = userDAO.getByLogin(userLogin);

        if (user == null || productCountList == null) {
            throw new NullPointerException();
        }
        for (ProductCount productCount : productCountList) {
            Long productQuantity = productCount.getCount();
            Long productId = productCount.getProductId();

            if (productCount == null)
                throw new ProductException("Null product value.");
            if (productQuantity < 1)
                throw new ProductException("Not enough products in cart.");
            if (productDAO.getCount(productId) < productQuantity)
                throw new ProductException("Not enough products available.");

            currencyAmount += productDAO.getById(productId).getPrice() * productQuantity;
        }
        if (userDAO.getAccount(user.getId()) < currencyAmount)
            throw new UserException("Not enough cash.");

        userDAO.withdraw(user.getId(), currencyAmount);
        productDAO.decrementCount(productCountList);
        paymentDAO.createFromPaylist(user.getId(), productCountList);
    }

    public Payment getById(Long Id) {
        return paymentDAO.getById(Id);
    }
}
