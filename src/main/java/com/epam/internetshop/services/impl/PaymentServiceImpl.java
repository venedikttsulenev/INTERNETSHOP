package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.PaymentDAO;
import com.epam.internetshop.DAO.impl.PaymentDAOImpl;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.validator.PaymentValidator;
import com.epam.internetshop.services.validator.impl.PaymentValidatorImpl;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAOImpl();
    private PaymentValidator paymentValidator = new PaymentValidatorImpl();

    public Payment create(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        return paymentDAO.create(payment);
    }

    public Payment update(Payment payment) {
        if (payment == null || !paymentValidator.validateAll(payment))
            return null;
        return paymentDAO.update(payment);
    }

    public void delete(Payment payment) {
        paymentDAO.delete(payment);
    }

    public List<Payment> getAll() {
        return paymentDAO.getAll();
    }

    public List<Payment> select(Payment payment) {
        return null;
    }

    public Payment getById(Long Id) {
        return paymentDAO.getById(Id);
    }

    public List<Payment> selectSort() {
        return null;
    }
}
