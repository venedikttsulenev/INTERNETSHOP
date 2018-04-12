package com.epam.internetshop.controllers;

import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.UserDAOImpl;
import com.epam.internetshop.DAO.util.HibernateSessionFactory;
import com.epam.internetshop.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = new User();
        user.setAdmin(false);
        user.setBlackListed(false);
        user.setLogin("fly2");
        user.setPassword("password");

        new UserDAOImpl().createUser(user);

        req.setAttribute("name", "Matt");


        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}
