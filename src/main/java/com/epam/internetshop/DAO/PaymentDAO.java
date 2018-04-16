package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Payment;

import java.util.List;

public interface PaymentDAO {
    Payment create(Payment entity);

    List<Payment> getAll();

    Payment getById(Long id);

    Payment update(Payment entity);

    void delete(Payment entity);
}
