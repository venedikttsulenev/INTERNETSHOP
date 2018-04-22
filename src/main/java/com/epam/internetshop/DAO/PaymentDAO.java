package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import org.hibernate.HibernateException;

import java.util.HashMap;
import java.util.List;

public interface PaymentDAO {
    Payment create(Payment entity);

    List<Payment> getAll();

    void createFromPaylist(Long userId, HashMap<Product, Long> productCountList) throws HibernateException;

    Payment getById(Long id);

    Payment update(Payment entity);

    void delete(Payment entity);
}
