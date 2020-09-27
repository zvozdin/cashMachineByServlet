package ua.com.training.service;

import ua.com.training.dao.DatabaseConnectionPool;
import ua.com.training.dao.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {

    private static final String SELECT_USER_WITH_LOGIN_AND_PASSWORD =
            "SELECT * FROM %s WHERE login='%s' AND password='%s'";
    private static final String SELECT_LOGINS_FROM_CASHIERS =
            "SELECT login FROM cashiers INNER JOIN roles ON role_id=id";
    private static final String SELECT_LOGINS_FROM_COMMODITY_EXPERTS =
            "SELECT login FROM commodity_experts inner JOIN roles ON role_id=id";

    @Override
    public boolean existUserByRoleAndLogin(String role, String login, String password) {
        String sql = String.format(SELECT_USER_WITH_LOGIN_AND_PASSWORD, getTableNameByRole(role), login, password);
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findAllCashiersLogin() {
        List<User> cashiers = new ArrayList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_LOGINS_FROM_CASHIERS)) {
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    User user = new User();
                    user.setLogin(login);
                    cashiers.add(user);
                }
            }
            return cashiers;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<User> findAllCommodityExpertsLogin() {
        List<User> experts = new ArrayList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_LOGINS_FROM_COMMODITY_EXPERTS)) {
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    User user = new User();
                    user.setLogin(login);
                    experts.add(user);
                }
            }
            return experts;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String getTableNameByRole(String role) {
        switch (role) {
            case "SENIOR_CASHIER":
                return "senior_cashier";
            case "CASHIER":
                return "cashiers";
            case "COMMODITY_EXPERT":
                return "commodity_experts";
        }
        return "";
    }
}