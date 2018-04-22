package com.epam.internetshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_.,!? ;:-]*$")
    @Size(min = 3,max = 50)
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9_.,!? ;:-]*$")
    @Size(min = 0,max = 200)
    private String description;

    @Column(nullable = false)
    @Min(0)
    private Long count;

    @Column(nullable = false)
    @Min(0)
    private Long price;

    public Product() {

    }

    public Product(String name, String description, Long count, Long price) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {

        return id.hashCode();
    }
}
