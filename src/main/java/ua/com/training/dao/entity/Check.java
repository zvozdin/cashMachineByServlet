package ua.com.training.dao.entity;

import java.sql.Timestamp;
import java.util.List;

public class Check {

    private String user;
    private Timestamp date;
    private int check_code;
    private List<Order> orders;

    public void setCheckCode(int check_code) {
        this.check_code = check_code;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setLogin(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getCheck_code() {
        return check_code;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
