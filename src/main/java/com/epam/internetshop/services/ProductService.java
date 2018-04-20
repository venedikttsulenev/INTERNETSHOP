package com.epam.internetshop.services;

import com.epam.internetshop.domain.Product;

import java.util.List;


public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    void delete(Product product);

    void increaseCount(Long productId, Long additionCount);

    List<Product> getAll();

    List<Product> getAllNameSorted(boolean isAsc);

    List<Product> getAllPriceSorted(boolean isAsc);

    List<Product> getAllCountSorted(boolean isAsc);

    Product getById(Long Id);

    boolean isEnoughProduct(Long productId, Long productQuantity);
}
