package DAO;

import javax.persistence.Id;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean isBlackListed;
    private boolean isAdmin;

    public User() {

    }
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(boolean blackListed) {
        isBlackListed = blackListed;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
