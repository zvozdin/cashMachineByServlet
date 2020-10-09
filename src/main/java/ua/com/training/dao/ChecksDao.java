package ua.com.training.dao;

import ua.com.training.dao.entity.Check;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChecksDao extends Dao {

    private static final String FIND_ALL_ORDERS = "" +
            "select checks.check_code as check_code, checks.dataTime as date, users.login as user " +
            "from users inner join checks on users.id = checks.user_id;";
    public static final String DELETE_CHECK_BY_CHECK_CODE = "DELETE FROM checks WHERE check_code=?";

    public List<Check> findAllChecks() {
        List<Check> checks = new LinkedList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS)) {
                while (resultSet.next()) {
                    Check check = new Check();
                    check.setCheckCode(resultSet.getInt("check_code"));
                    check.setDate(resultSet.getTimestamp("date"));
                    check.setLogin(resultSet.getString("user"));
                    checks.add(check);
                }
                return checks;
            }
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public boolean deleteOrderByCheckCode(int checkCode) throws Exception {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CHECK_BY_CHECK_CODE)
        ) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement.setInt(1, checkCode);
            if (statement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();

        }

        return false;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(new ChecksDao().findAllChecks().toString());
//        System.out.println(new ChecksDao().deleteOrderByCheckCode();
    }
}
