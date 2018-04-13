package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.ProductDAO;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.ProductService;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private DAO<Product> productDAO = new ProductDAO();

    public Product create(Product product) {
        if (checkFieldsNULL(product))
            return null;
        return productDAO.create(product);
    }

    public Product update(Product product) {
        return productDAO.update(product);
    }

    public void delete(Product product) {
        productDAO.delete(product);
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public List<Product> select(Product product) {
        return null;
    }

    public Product getById(Long Id) {
        return productDAO.getById(Id);
    }

    public List<Product> selectSort() {
        return null;
    }

    private boolean checkFieldsNULL(Product product) {
        return (product.getPrice() == null || product.getName() == null ||
                product.getCount() == null || product.getDescription() == null);
    }
}
