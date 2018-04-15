package com.epam.internetshop.services.validator;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;

import java.util.Date;

public interface PaymentValidator {

    boolean validateAll(Payment payment);

    boolean validateUser(User user);

    boolean validateProduct(Product product);

    boolean validatePrice(Long price);

    boolean validatePaydate(Date paydate);
}
