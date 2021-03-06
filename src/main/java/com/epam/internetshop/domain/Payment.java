package com.epam.internetshop.domain;

import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(nullable=false)
    @Min(0)
    private Long price;

    @Temporal(value = TemporalType.DATE)
    private Date paydate;

    @Column(nullable=false)
    @Min(1)
    private Long count;

    public Payment() {
    }
    public Payment(User userId, Product productId, Long price, Long count, Date paydate){
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.count = count;
        this.paydate = paydate;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
