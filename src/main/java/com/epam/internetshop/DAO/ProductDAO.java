package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.exception.ProductException;

import java.util.HashMap;
import java.util.List;

public interface ProductDAO {
    Product create(Product entity);

    List<Product> getAll();

    Product getById(Long id);

    Product update(Product entity);

    void delete(Product entity);

    Long getCount(Long id);

    Product setCount(Long productId, Long productCount) throws ProductException;

    void increaseCount(Long productId, Long additionalCount) throws ProductException;

    void decrementCount(Long productId, Long decrementCount) throws ProductException;

    void decrementCount(HashMap<Product, Long> productCountList) throws ProductException;

    List<Product> getAllSorted(String columnName, boolean isAsc);

}
