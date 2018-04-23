package com.epam.internetshop.services.impl;

import com.epam.internetshop.DAO.DAOFactory;
import com.epam.internetshop.DAO.UserDAO;
import com.epam.internetshop.DAO.impl.HibernateDAOFactory;
import com.epam.internetshop.domain.User;
import com.epam.internetshop.services.UserService;
import com.epam.internetshop.services.exception.UserException;
import com.epam.internetshop.services.validator.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static DAOFactory daoFactory = new HibernateDAOFactory();

    private UserDAO userDAO = daoFactory.newUserDAO();
    private UserValidator userValidator = ServiceFactory.newUserValidator();

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public User create(User user) {
        if (user == null || !userValidator.validateAll(user)) {
            logger.error("Can't create user.");
            return null;
        }
        if (getByLogin(user.getLogin()) != null) {
            logger.error("Can't create user.");
            return null;
        }
        return userDAO.create(user);
    }

    public User update(User user) {
        if (user == null || !userValidator.validateAll(user)) {
            logger.error("Can't update user.");
            return null;
        }
        return userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User createUser(String login, String password) {
        if (login == null || password == null ||
                !userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            logger.error("Can't create user.");
            return null;
        }
        if (getByLogin(login) != null) {
            logger.error("Can't create user.");
            return null;
        }
        User user = new User(login, password);
        return userDAO.create(user);
    }

    public User getByLogin(String login) {
        if (login == null || !userValidator.validateLogin(login))
            return null;
        return userDAO.getByLogin(login);
    }

    public User getById(Long Id) {
        if (Id == null)
            return null;
        return userDAO.getById(Id);
    }

    public boolean isEnoughCurrency(Long userId, Long currencyAmount) {
        if (userId == null || currencyAmount == null) {
            throw new UserException("Null values.");
        }
        if (currencyAmount < 0) {
            logger.error("Can't withdraw. Can't withdraw less than zero.");
            throw new UserException("Can't withdraw less than zero.");
        }
        return userDAO.getAccount(userId) >= currencyAmount;
    }

    public User increaseAccount(String login, Long currencyAmount) {
        if (login == null || currencyAmount == null) {
            throw new UserException("Null values.");
        }
        if (currencyAmount <= 0) {
            logger.error("Can't increase account. Can't increase on zero or less.");
            throw new UserException("Can't increase on zero or less.");
        }
        User user = userDAO.getByLogin(login);
        userDAO.incrementAccount(user.getId(), currencyAmount);
        return userDAO.getById(user.getId());
    }

    public User setBlackListed(String userLogin, boolean isBlackListed) {
        if (userLogin == null) {
            throw new NullPointerException();
        }
        User user = userDAO.getByLogin(userLogin);
        return userDAO.setBlackListed(user.getId(), isBlackListed);
    }

    public Boolean isAdmin(String login) {
        return userDAO.isAdmin(login);
    }

    public Boolean isBlackListed(String login) {
        return userDAO.isBlackListed(login);
    }
}
