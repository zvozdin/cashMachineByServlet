package ua.com.training.dao;

import ua.com.training.dao.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_FROM_ROLE_TABLE =
            "SELECT * FROM %s WHERE login='%s' AND password='%s'";

    public User findUserByRoleAndLoginAndPassword(String role, String login, String password) {
        User user = new User();
        String sql = String.format(
                FIND_USER_BY_LOGIN_AND_PASSWORD_FROM_ROLE_TABLE, getTableNameByRole(role), login, password);
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    user.setId(Long.parseLong(resultSet.getString("id")));
                    user.setLogin(resultSet.getString("login"));
                }
            }
            return user;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return user;
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