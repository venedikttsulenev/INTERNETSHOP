package com.epam.internetshop.services.validator.impl;

import com.epam.internetshop.domain.Product;
import com.epam.internetshop.services.validator.ProductValidator;

public class ProductValidatorImpl implements ProductValidator {

    @Override
    public boolean validateAll(Product product) {
        if (product == null)
            return false;
        if (!validateName(product.getName()))
            return false;
        if (!validateDescription(product.getDescription()))
            return false;
        if (!validateCount(product.getCount()))
            return false;
        if (!validatePrice(product.getPrice()))
            return false;
        return true;
    }

    @Override
    public boolean validateName(String name) {
        if (name == null)
            return false;
        if (!isSuitable(name))
            return false;
        return true;
    }

    @Override
    public boolean validateDescription(String description) {
        if (description == null)
            return false;
        if (!isSuitable(description))
            return false;
        if (!isRightDescriptionLength(description))
            return false;
        return true;
    }

    @Override
    public boolean validateCount(Long count) {
        if (count == null)
            return false;
        if (!isRightCount(count))
            return false;
        return true;
    }

    @Override
    public boolean validatePrice(Long price) {
        if (price == null)
            return false;
        if (!isRightPrice(price))
            return false;
        return true;
    }

    private boolean isSuitable(String string) {
        return true;
    }

    private boolean isRightCount(Long count){return count >=0;}

    private boolean isRightPrice(Long price){return price >=0;}

    private boolean isRightLength(String string) {
        return string.length() >= 3 && string.length() <= 40;
    }

    private boolean isRightDescriptionLength(String string) {
        return string.length() >= 0 && string.length() <= 200;
    }
}
