package ua.com.training.dao.entity;

import java.sql.Timestamp;

public class Check {

    private String login;
    private Timestamp date;
    private int checkCode;
    private Order order;

    public Check(CheckBuilder checkBuilder) {
        this.login = checkBuilder.login;
        this.date = checkBuilder.date;
        this.checkCode = checkBuilder.checkCode;
        this.order = checkBuilder.order;
    }

    public String getLogin() {
        return login;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getCheckCode() {
        return checkCode;
    }

    public Order getOrder() {
        return order;
    }

    public static class CheckBuilder {

        private String login;
        private Timestamp date;
        private int checkCode;
        private Order order;

        public CheckBuilder() {
        }

        public CheckBuilder login(String login) {
            this.login = login;
            return this;
        }

        public CheckBuilder date(Timestamp date) {
            this.date = date;
            return this;
        }

        public CheckBuilder checkCode(int checkCode) {
            this.checkCode = checkCode;
            return this;
        }
        public CheckBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public Check build() {
            return new Check(this);
        }
    }
}
