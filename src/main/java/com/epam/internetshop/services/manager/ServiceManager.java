package com.epam.internetshop.services.manager;

import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.PaymentServiceImpl;
import com.epam.internetshop.services.impl.ProductServiceImpl;
import com.epam.internetshop.services.impl.UserServiceImpl;
import com.epam.internetshop.services.validator.PaymentValidator;
import com.epam.internetshop.services.validator.ProductValidator;
import com.epam.internetshop.services.validator.UserValidator;
import com.epam.internetshop.services.validator.impl.PaymentValidatorImpl;
import com.epam.internetshop.services.validator.impl.ProductValidatorImpl;
import com.epam.internetshop.services.validator.impl.UserValidatorImpl;

public class ServiceManager {

    public static UserService newUserService() {
        return new UserServiceImpl();
    }

    public static ProductService newProductService() {
        return new ProductServiceImpl();
    }

    public static PaymentService newPaymentService() {
        return new PaymentServiceImpl();
    }

    public static UserValidator newUserValidator() { return new UserValidatorImpl(); }

    public static ProductValidator newProductValidator() { return new ProductValidatorImpl(); }

    public static PaymentValidator newPaymentValidator() { return new PaymentValidatorImpl(); }
}
