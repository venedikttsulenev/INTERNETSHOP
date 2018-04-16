package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product create(Product entity);

    List<Product> getAll();

    Product getById(Long id);

    Product update(Product entity);

    void delete(Product entity);

    Product decrementCount(Long id);

    List<Product> decrementCount(List<Long> id);
}
