package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;

import java.util.HashMap;
import java.util.List;

public interface PaymentDAO {
    Payment create(Payment entity);

    List<Payment> getAll();

    public List<Payment> getPage(int pageSize, int page);

    void createFromPaylist(Long userId, HashMap<Long, Long> productCountList);

    void performPurchase(Long userId, HashMap<Long, Long> productCountList, Long withdrawAmount);

    Payment getById(Long id);

    Payment update(Payment entity);

    void delete(Payment entity);
}
