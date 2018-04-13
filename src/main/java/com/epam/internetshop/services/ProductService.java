package com.epam.internetshop.services;

import com.epam.internetshop.domain.Product;

import java.util.List;


public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    void delete(Product product);

    List<Product> getAll();

    List<Product> select(Product product);

    Product getById(Long Id);

    List<Product> selectSort();

}
