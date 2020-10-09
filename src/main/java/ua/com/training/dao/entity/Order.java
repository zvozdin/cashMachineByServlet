package ua.com.training.dao.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {

    private Long userId;
    private String login;
    private Integer checkCode;
    private Timestamp date;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
