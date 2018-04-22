package com.epam.internetshop.services.validator.impl;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.validator.PaymentValidator;

import java.util.Date;

public class PaymentValidatorImpl implements PaymentValidator {
    @Override
    public boolean validateAll(Payment payment) {
        if (payment == null)
            return false;
        if (!validateUser(payment.getUserId()))
            return false;
        if (!validateProduct(payment.getProductId()))
            return false;
        if (!validatePaydate(payment.getPaydate()))
            return false;
        if (!validatePrice(payment.getPrice()))
            return false;
        return true;
    }

    @Override
    public boolean validateUser(User user) {
        if (user == null)
            return false;
        return true;
    }

    @Override
    public boolean validateProduct(Product product) {
        if (product == null)
            return false;
        return true;
    }

    @Override
    public boolean validatePrice(Long price) {
        if (price == null)
            return false;
        if (!isRightPrice(price))
            return false;
        return true;
    }

    @Override
    public boolean validatePaydate(Date paydate) {
        if (paydate == null)
            return false;
        return true;
    }

    private boolean isRightPrice(Long price) {
        return price >= 0;
    }
}
