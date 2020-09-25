package ua.com.training.service;

import ua.com.training.dao.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceImpl implements Service {

    private static final String SQL_FIND_SENIOR_BY_LOGIN = "select * from senior_cashier where login=? and password=?";

    @Override
    public boolean existSeniorCashierByLogin(String login, String password) {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_SENIOR_BY_LOGIN)
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
