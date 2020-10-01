package ua.com.training.dao;

import ua.com.training.dao.entity.Roles;
import ua.com.training.dao.entity.User;

import java.sql.*;

public class UserDao {

    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT u.login, r.role FROM users u JOIN roles r ON u.role_id=r.id WHERE u.login=? AND u.password=?";

    public User findUserByLoginAndPassword(String login, String password) {
        User user = new User();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user.setLogin(resultSet.getString("login"));
                    user.setRole(Roles.valueOf(resultSet.getString("role")));
                }
            }
            return user;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return user;
    }
}
