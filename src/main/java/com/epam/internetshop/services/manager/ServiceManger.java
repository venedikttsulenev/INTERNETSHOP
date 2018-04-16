package com.epam.internetshop.services.manager;

import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.PaymentServiceImpl;
import com.epam.internetshop.services.impl.ProductServiceImpl;
import com.epam.internetshop.services.impl.UserServiceImpl;

public class ServiceManger {

    public static UserService newUserService() {
        return new UserServiceImpl();
    }

    public static ProductService newProductService() {
        return new ProductServiceImpl();
    }

    public static PaymentService newPaymentService() {
        return new PaymentServiceImpl();
    }
}
