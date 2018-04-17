package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;

import java.util.List;

public interface PaymentDAO {
    Payment create(Payment entity);

    List<Payment> getAll();

    void createFromPaylist(User user, List<ProductCount> productCountList);

    Payment getById(Long id);

    Payment update(Payment entity);

    void delete(Payment entity);
}
