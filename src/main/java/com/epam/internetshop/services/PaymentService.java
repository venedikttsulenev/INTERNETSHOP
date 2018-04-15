package com.epam.internetshop.services;

import com.epam.internetshop.domain.Payment;

import java.util.List;


public interface PaymentService {
    Payment create(Payment payment);

    Payment update(Payment payment);

    void delete(Payment payment);

    List<Payment> getAll();

    @Deprecated
    List<Payment> select(Payment payment);

    Payment getById(Long Id);

    @Deprecated
    List<Payment> selectSort();

}
