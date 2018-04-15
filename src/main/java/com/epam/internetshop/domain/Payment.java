package com.epam.internetshop.domain;

import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    @Column(nullable=false)
    private Long price;
    @Temporal(value = TemporalType.DATE)
    private Date paydate;
    @Column(nullable=false)
    private Boolean isPayed;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userID) {
        this.userId = userID;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productID) {
        this.productId = productID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Boolean isPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }
}
