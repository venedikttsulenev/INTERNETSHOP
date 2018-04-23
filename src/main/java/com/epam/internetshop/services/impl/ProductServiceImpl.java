package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAOFactory;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.impl.HibernateDAOFactory;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.exception.ProductException;
import com.epam.internetshop.services.validator.ProductValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    private static final DAOFactory daoFactory = new HibernateDAOFactory();

    private ProductDAO productDAO = daoFactory.newProductDAO();
    private ProductValidator productValidator = ServiceFactory.newProductValidator();

    public Product create(Product product) {
        if (product == null || !productValidator.validateAll(product)) {
            logger.error("Can't create product.");
            return null;
        }
        return productDAO.create(product);
    }

    public Product update(Product product) {
        if (product == null || !productValidator.validateAll(product)) {
            logger.error("Can't update product.");
            return null;
        }
        return productDAO.update(product);
    }

    public void delete(Product product) {
        productDAO.delete(product);
    }

    public void increaseCount(Long productId, Long additionCount) {
        if (productId == null || additionCount == null || additionCount < 1) {
            logger.error("Can't increase product count.");
            throw new ProductException();
        }
        productDAO.increaseCount(productId, additionCount);
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public Product getById(Long Id) {
        return productDAO.getById(Id);
    }

    public Product setCount(Long productId, Long productCount) {
        if (productId == null || productCount == null || productCount < 0) {
            logger.error("Can't set product count.");
            throw new ProductException();
        }
        return productDAO.setCount(productId, productCount);
    }

    public boolean isEnoughProduct(Long productId, Long productQuantity) throws ProductException {
        Long count = productDAO.getCount(productId);
        if (count == null) {
            logger.error("Null value");
            throw new ProductException("Null value");
        }
        return count >= productQuantity;
    }

    public List<Product> getAllNameSorted(boolean isAsc) {
        return productDAO.getAllSorted("productName", isAsc);
    }

    public List<Product> getAllPriceSorted(boolean isAsc) {
        return productDAO.getAllSorted("price", isAsc);
    }

    public List<Product> getAllCountSorted(boolean isAsc) {
        return productDAO.getAllSorted("count", isAsc);
    }
}
