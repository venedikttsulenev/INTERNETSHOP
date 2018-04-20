package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;
import com.epam.internetshop.domain.User;

import java.util.List;

public interface PaymentDAO {
    Payment create(Payment entity);

    List<Payment> getAll();

    void createFromPaylist(Long userId, List<ProductCount> productCountList);

    void performPayOperation(Long userId, List<ProductCount> productCountList, Long withdrawAmount);

    Payment getById(Long id);

    Payment update(Payment entity);

    void delete(Payment entity);
}
