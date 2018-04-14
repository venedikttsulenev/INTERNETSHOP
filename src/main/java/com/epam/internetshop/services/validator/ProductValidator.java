package com.epam.internetshop.services.validator;

import com.epam.internetshop.domain.Product;

public interface ProductValidator {
    boolean validateAll(Product product);
    boolean validateName(String name);
    boolean validateDescription(String description);
    boolean validateCount(Long count);
    boolean validatePrice(Long price);
}
