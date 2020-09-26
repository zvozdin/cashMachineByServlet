package ua.com.training.service;

import ua.com.training.dao.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceImpl implements Service {

    @Override
    public boolean existUserByRoleAndLogin(String role, String login, String password) {
        String sql = String.format(("select * from %s where login='%s' and password='%s'"),
                getTableNameByRole(role), login, password);
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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