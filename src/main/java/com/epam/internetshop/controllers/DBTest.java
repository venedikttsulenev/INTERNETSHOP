package com.epam.internetshop.controllers;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.PaymentServiceImpl;
import com.epam.internetshop.services.impl.ProductServiceImpl;
import com.epam.internetshop.services.impl.UserServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.PropertyValueException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/dbtest")
public class DBTest extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private PaymentService paymentService = new PaymentServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        DAO<User> dao = new UserDAO();
    }



    private void checkWrongValue(PrintWriter out) {
            User usr = new User();
            usr.setPassword("p1");
            usr.setLogin("u1");
            usr.setAdmin(false);
            usr.setBlackListed(false);
            userService.create(usr);

            usr = new User();
            usr.setPassword("p2");
            usr.setLogin("u2");
            usr.setAdmin(false);
            usr.setBlackListed(false);
            userService.create(usr);
    }

    private void testPayment(PrintWriter out) {
        User user = createUser();
        Product product = createProduct();
        createPayment(product, user);
        createPayment(product, user);
        createPayment(product, user);
        createPayment(product, user);

        List<Payment> list = paymentService.getAll();

        for (Payment payment1 : list) {
            out.println("<h1>" + payment1.getPaydate().toString() + "</h1>");
            paymentService.delete(payment1);
        }
    }

    private User createUser() {
        User usr = new User();
        usr.setPassword("passpass");
        usr.setLogin("log");
        usr.setAdmin(false);
        usr.setBlackListed(false);
        userService.create(usr);
        return usr;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setDescription("a");
        product.setCount((long) 5);
        product.setName("n");
        product.setPrice((long) 10);
        productService.create(product);
        return product;
    }

    private Payment createPayment(Product product, User user) {
        Payment payment = new Payment();
        payment.setProductId(product);
        payment.setUserId(user);
        payment.setPaydate(new Date());
        payment.setPrice(product.getPrice());
        payment.setPayed(true);
        paymentService.create(payment);
        return payment;
    }
}
