package ua.com.training.dao;

import ua.com.training.dao.entity.Check;
import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChecksDao extends Dao {

    private static final String FIND_ALL_ORDERS = "" +
            "select checks.check_code as check_code, checks.dataTime as date, users.login as user " +
            "from users inner join checks on users.id = checks.user_id;";

    private static final String DELETE_CHECK_BY_CHECK_CODE = "DELETE FROM checks WHERE check_code=?";

    private static final String SELECT_ORDER_BY_CHECK_CODE = "" +
            "select checks.check_code, stock.id, stock.code, stock.name, stock.name_UA, stock.size, orders.price, orders.quantity, orders.bill " +
            "from checks " +
            "inner join orders on checks.id = orders.check_id " +
            "inner join stock on orders.product_id = stock.id " +
            "where checks.check_code = ?";

    public List<Check> findAllChecks() {
        List<Check> result = new LinkedList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS)) {
                while (resultSet.next()) {
                    result.add(
                            new Check.CheckBuilder()
                                    .checkCode(resultSet.getInt("check_code"))
                                    .login(resultSet.getString("user"))
                                    .date(resultSet.getTimestamp("date"))
                                    .build());
                }
            }

            return result;
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Check> findAllChecksWithProducts() {
        List<Check> result = new LinkedList<>();
        for (Check check : findAllChecks()) {
            Order order = findOrderByCheckCode(check.getCheckCode());
            if (order.getProducts().isEmpty()) {
                return result;
            }

            result.add(
                    new Check.CheckBuilder()
                            .checkCode(check.getCheckCode())
                            .date(check.getDate())
                            .login(check.getLogin())
                            .order(order)
                            .build());
        }

        return result;
    }

    public boolean deleteOrderByCheckCode(int checkCode) {
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
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }

        return false;
    }

    public Order findOrderByCheckCode(int checkCode) {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_CHECK_CODE)
        ) {
            List<Product> products = new ArrayList<>();
            statement.setInt(1, checkCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product.ProductBuilder()
                            .id(Long.valueOf(resultSet.getInt("id")))
                            .code(resultSet.getString("code"))
                            .name(resultSet.getString("name"))
                            .name_UA(resultSet.getString("name_UA"))
                            .size(Size.valueOf(resultSet.getString("size")))
                            .price(resultSet.getDouble("price"))
                            .quantity(resultSet.getInt("quantity"))
                            .bill(resultSet.getDouble("bill"))
                            .build());
                }
            }

            return new Order.OrderBuilder()
                    .checkCode(checkCode)
                    .products(products)
                    .build();
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }

        return new Order.OrderBuilder().build();
    }
}
