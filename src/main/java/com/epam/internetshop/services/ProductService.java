package com.epam.internetshop.services;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.exception.ProductException;

import java.util.List;


public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    void delete(Product product);

    void increaseCount(Long productId, Long additionCount) throws ProductException;

    List<Product> getAll();

    List<Product> getAllNameSorted(boolean isAsc);

    List<Product> getAllPriceSorted(boolean isAsc);

    List<Product> getAllCountSorted(boolean isAsc);

    Product getById(Long Id);

    Product setCount(Long productId, Long productCount) throws ProductException;

    boolean isEnoughProduct(Long productId, Long productQuantity) throws ProductException;
}
