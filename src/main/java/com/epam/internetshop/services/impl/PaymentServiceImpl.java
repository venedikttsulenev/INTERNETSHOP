package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.PaymentDAO;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.services.PaymentService;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private DAO<Payment> paymentDAO = new PaymentDAO();

    public Payment create(Payment payment) {
        if (checkFieldsNULL(payment)) return null;
        return paymentDAO.create(payment);
    }

    public Payment update(Payment payment) {
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

    private boolean checkFieldsNULL(Payment payment) {
        return (payment.getPrice() == null || payment.getPaydate() == null ||
                payment.getProductId() == null || payment.getUserId() == null);
    }
}
