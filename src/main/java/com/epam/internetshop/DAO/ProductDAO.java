package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.ProductCount;

import java.util.List;

public interface ProductDAO {
    Product create(Product entity);

    List<Product> getAll();

    Product getById(Long id);

    Product update(Product entity);

    void delete(Product entity);

    Long getCount(Long id);

    void decrementCount(List<ProductCount> productCountList);

    List<Product> getAllSorted(String columnName, boolean isAsc);

    }
