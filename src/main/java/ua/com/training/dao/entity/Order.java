package ua.com.training.dao.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {

    private Long userId;
    private String login;
    private Integer checkCode;
    private Timestamp date;
    private List<Product> products;

    private Order(OrderBuilder orderBuilder) {
        this.userId = orderBuilder.userId;
        this.login = orderBuilder.login;
        this.checkCode = orderBuilder.checkCode;
        this.date = orderBuilder.date;
        this.products = orderBuilder.products;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Integer getCheckCode() {
        return checkCode;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getLogin() {
        return login;
    }

    public static class OrderBuilder {

        private Long userId;
        private String login;
        private Integer checkCode;
        private Timestamp date;
        private List<Product> products;

        public OrderBuilder() {
        }

        public OrderBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder login(String login) {
            this.login = login;
            return this;
        }

        public OrderBuilder checkCode(Integer checkCode) {
            this.checkCode = checkCode;
            return this;
        }

        public OrderBuilder date(Timestamp date) {
            this.date = date;
            return this;
        }

        public OrderBuilder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
