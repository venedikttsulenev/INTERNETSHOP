package com.epam.internetshop.controllers.manager;

import com.epam.internetshop.DAO.impl.UserDAO;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
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
import java.util.List;

@WebServlet("/dbtest")
public class DBTest extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //userService.getById(new Long(99));
        checkWrongValue(out);

        List<User> list = userService.getAll();

        for (User user : list) {
            out.println("<h1>" + user.getLogin() + "</h1>");
        }

    }

    private void checkWrongValue(PrintWriter out) {
        try {
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
        } catch (PropertyValueException e) {
            out.println("Wrong value.");
        } catch (PropertyNotFoundException e) {
            out.println("Not Fount Property.");
        }
    }
}
