package ua.com.training.dao;

import ua.com.training.dao.entity.Check;
import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ChecksDao extends Dao {

    private static final String FIND_ALL_ORDERS = "" +
            "select checks.check_code as check_code, checks.dataTime as date, users.login as user " +
            "from users inner join checks on users.id = checks.user_id;";

    private static final String DELETE_CHECK_BY_CHECK_CODE = "DELETE FROM checks WHERE check_code=?";

    private static final String SELECT_ORDER_BY_CHECK_CODE = "" +
            "select checks.check_code, stock.code, stock.name, stock.size, orders.price, orders.quantity, orders.bill " +
            "from checks " +
            "inner join orders on checks.id = orders.check_id " +
            "inner join stock on orders.product_id = stock.id " +
            "where checks.check_code = ?";

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
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();

        }

        return false;
    }

    public Optional<Order> findOrderByCheckCode(int checkCode) throws Exception {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_CHECK_CODE)
        ) {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            List<Product> products = new ArrayList<>();
            statement.setInt(1, checkCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product.ProductBuilder()
                            .code(resultSet.getString("code"))
                            .name(resultSet.getString("name"))
                            .size(Size.valueOf(resultSet.getString("size").toUpperCase()))
                            .price(resultSet.getDouble("price"))
                            .quantity(resultSet.getInt("quantity"))
                            .bill(resultSet.getDouble("bill"))
                            .build());
                }
            }

            return Optional.ofNullable(
                    new Order.OrderBuilder()
                            .checkCode(checkCode)
                            .products(products)
                            .build());
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }

        return Optional.ofNullable(null);
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(new ChecksDao().findAllChecks().toString());
//        System.out.println(new ChecksDao().deleteOrderByCheckCode();
//        System.out.println(new ChecksDao().findOrderByCheckCode(1000));
        System.out.println(new OrdersDao()
                .deleteProductByCheckCodeAndProductCode(
                        new Product.ProductBuilder()
                                .id(Long.valueOf(1))
                                .build(), 1000));
    }
}
