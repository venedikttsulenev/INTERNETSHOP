package com.epam.internetshop.services;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;

import java.util.List;


public interface PaymentService {
    Payment create(Payment payment);

    Payment update(Payment payment);

    void delete(Payment payment);

    List<Payment> getAll();

    List<Payment> performPayment(User user, List<Long> productIdList);

    Payment getById(Long Id);

}
