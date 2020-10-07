package ua.com.training.dao.entity;

import java.util.List;

public class Order {

    private Long userId;
    private Integer checkCode;
    private List<Product> products;

    public Order() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCheckCode(int checkCode) {
        this.checkCode = checkCode;
    }

    public Integer getCheckCode() {
        return checkCode;
    }
}
