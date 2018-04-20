package com.epam.internetshop.DAO;

import com.epam.internetshop.domain.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductDAO {
    Product create(Product entity);

    List<Product> getAll();

    List<Product> getPage(int pageSize, int page);

    Product getById(Long id);

    Product update(Product entity);

    void delete(Product entity);

    Long getCount(Long id);

    void increaseCount(Long productId, Long additionalCount);

    void decrementCount(Long productId, Long decrementCount);

    void decrementCount(HashMap<Long, Long> productCountList);

    void changePrice(Long productId, Long price);

    List<Product> getAllSorted(String columnName, boolean isAsc);
}
