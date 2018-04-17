package com.epam.internetshop.domain;

public class ProductCount {
    private Product product;
    private Long count;
    public ProductCount(Product product, Long count){
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
