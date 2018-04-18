package com.epam.internetshop.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long account;
    @Column(nullable = false)
    private Boolean isBlackListed;
    @Column(nullable = false)
    private Boolean isAdmin;

    public User() {

    }

    public User(String login, String password, Long account,
                Boolean isBlackListed, Boolean isAdmin){
        this.login = login;
        this.password = password;
        this.account = account;
        this.isBlackListed = isBlackListed;
        this.isAdmin = isAdmin;
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.account = 0L;
        this.isBlackListed = false;
        this.isAdmin = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(Boolean blackListed) {
        isBlackListed = blackListed;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}
