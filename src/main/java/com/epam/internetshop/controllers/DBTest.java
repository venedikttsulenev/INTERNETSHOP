package com.epam.internetshop.controllers;

import com.epam.internetshop.DAO.DAO;
import com.epam.internetshop.DAO.ProductDAO;
import com.epam.internetshop.DAO.impl.ProductDAOImpl;
import com.epam.internetshop.DAO.impl.UserDAOImpl;
import com.epam.internetshop.domain.Payment;
import com.epam.internetshop.domain.Product;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.PaymentService;
import com.epam.internetshop.services.ProductService;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.impl.PaymentServiceImpl;
import com.epam.internetshop.services.impl.ProductServiceImpl;
import com.epam.internetshop.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

        //checkUserservice(out);
        //testPaymentList(out);
        testDecr(out);
    }


    private void checkUserservice(PrintWriter out) {
        User usr = new User();
        usr.setPassword("12345678");
        usr.setLogin("u1");
        usr.setAdmin(false);
        usr.setBlackListed(false);
        userService.create(usr);

        usr = userService.getById(usr.getId());
        out.println(usr.getId() + " " + usr.getPassword() + " <br>");

        usr = userService.getByLogin(usr.getLogin());
        out.println(usr.getId() + " " + usr.getPassword() + " <br>");

        usr.setPassword("87654321");
        userService.update(usr);
        out.println(usr.getId() + " " + usr.getPassword() + " <br>");

        try {
            userService.delete(usr);
            usr = userService.getByLogin(usr.getLogin());
            out.println(usr.getId() + " " + usr.getPassword() + " <br>");
        } catch (Throwable e) {
            out.println(e.getMessage() + " <br>");
        }

        usr = new User();
        usr.setPassword("12345678");
        usr.setLogin("u2");
        usr.setAdmin(false);
        usr.setBlackListed(false);
        userService.create(usr);

        try {
            usr = new User();
            usr.setPassword("12345678");
            usr.setLogin("u2");
            usr.setAdmin(false);
            usr.setBlackListed(false);
            userService.create(usr);
        } catch (Throwable e) {
            out.println(e.getMessage() + " <br>");
        }

        try {
            List<User> list = userService.getAll();
            for (User user : list) {
                out.println(user.getId() + " " + user.getPassword() + " <br>");
            }
        } catch (Throwable e) {
            out.println(e.getMessage() + " <br>");
        }
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

    private void testDecr(PrintWriter out) {
        ProductDAO productDAO = new ProductDAOImpl();
        Product product = new Product();
        product.setDescription("a");
        product.setCount(5l);
        product.setName("n");
        product.setPrice(10l);
        productDAO.create(product);
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
        product = productDAO.decrementCount(product.getId());
        out.println(product.getCount()+"<br>");
    }

    private void testPaymentList(PrintWriter out) {
        User usr = new User();
        usr.setPassword("12345678");
        usr.setLogin("u1");
        usr.setAdmin(false);
        usr.setBlackListed(false);
        userService.create(usr);

        Product product = new Product();
        product.setId(10l);
        product.setPrice(100l);
        product.setName("a");
        product.setCount(2l);
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < 5; i++)
            list.add(product);
        List<Payment> paymentList =
                paymentService.performPayment(usr, list);
        for (Payment payment : paymentList) {
            out.println(payment.getProductId() + " " + payment.getUserId() + "<br>");
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
        product.setCount( 5l);
        product.setName("n");
        product.setPrice(10l);
        productService.create(product);
        return product;
    }

    private Payment createPayment(Product product, User user) {
        Payment payment = new Payment();
        payment.setProductId(product);
        payment.setUserId(user);
        payment.setPaydate(new Date());
        payment.setPrice(product.getPrice());
        paymentService.create(payment);
        return payment;
    }
}
