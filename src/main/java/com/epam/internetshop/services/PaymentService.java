package com.epam.internetshop.services;

import com.epam.internetshop.domain.Payment;

import java.util.HashMap;
import java.util.List;


public interface PaymentService {
    Payment create(Payment payment);

    Payment update(Payment payment);

    void delete(Payment payment);

    List<Payment> getAll();

    List<Payment> getPage(int pageSize, int page);

    void performPayment(String userLogin, HashMap<Long, Long> productCountList);

    void performPurchase(String userLogin, HashMap<Long, Long> productCountList);

    Payment getById(Long Id);
}
