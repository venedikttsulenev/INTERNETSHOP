package com.epam.internetshop.services;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.exception.UserException;

import java.util.HashMap;
import java.util.List;


public interface PaymentService {
    Payment create(Payment payment);

    Payment update(Payment payment);

    void delete(Payment payment);

    List<Payment> getAll();

    void performPayment(String userLogin, HashMap<Product, Long> productCountList) throws UserException, ProductException;

    Payment getById(Long Id);

}
