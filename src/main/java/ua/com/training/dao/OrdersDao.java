package ua.com.training.dao;

import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Report;
import ua.com.training.dao.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class OrdersDao {

    private static final String SELECT_MAX_CHECK_CODE = "SELECT MAX(check_code) AS max_check_code FROM checks";

    private static final String FIND_PRODUCTS_QUANTITY = "select quantity from stock where id = ?";

    private static final String ADD_CHECK_BY_USER = "" +
            "insert into checks(user_id, check_code)" +
            "values (?, ?)";

    private static final String INSERT_ORDER = "" +
            "insert into orders(check_id, product_id, quantity, price, bill) " +
            "values (?, ?, ?, ?, ?)";

    private static final String SUBTRACTION_QUANTITY = "update stock set quantity = quantity - ? where id = ?";

    private static final String DELETE_PRODUCT_FROM_ORDER = "delete p from orders p " +
            "inner join checks c on p.check_id = c.id  where p.product_id = ? and c.check_code = ?";

    private static final String SELECT_CHECKS_COUNT = "" +
            "select count(check_code) as count from checks where DATE(dataTime) = CURDATE()";

    private static final String SELECT_QUANTITY_AND_BILL_COUNT = "" +
            "select sum(orders.quantity) as quantity, sum(orders.bill) as bill from orders " +
            "inner join checks on orders.check_id = checks.id where DATE(checks.dataTime) = CURDATE()";

    public Optional<Report> findOrdersBillAndProductQuantitySum() {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement checksCount = connection.createStatement();
             Statement quantityAndBillCount = connection.createStatement()
        ) {
            int count = 0;
            int quantity = 0;
            double bill = 0;
            try (ResultSet resultSet = checksCount.executeQuery(SELECT_CHECKS_COUNT)) {
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
            try (ResultSet resultSet = quantityAndBillCount.executeQuery(SELECT_QUANTITY_AND_BILL_COUNT)) {
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                    bill = resultSet.getDouble("bill");
                }
            }

            return Optional.ofNullable(new Report.ReportBuilder()
                    .checksCount(count)
                    .quantity(quantity)
                    .bill(bill)
                    .build());
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean deleteProductByCheckCodeAndProductCode(Product product, int checkCode) throws Exception {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_FROM_ORDER)
        ) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement.setInt(1, Math.toIntExact(product.getId()));
            statement.setInt(2, checkCode);
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

    public int save(List<Product> products, User user) {

        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement selectStockProductQuantity = connection.prepareStatement(FIND_PRODUCTS_QUANTITY);
             Statement selectMaxCheckCodeInChecks = connection.createStatement();
             PreparedStatement addCheckCode = connection.prepareStatement(ADD_CHECK_BY_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insert = connection.prepareStatement(INSERT_ORDER)
        ) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            if (stockQuantityLessThanClientOrder(products, selectStockProductQuantity)) {
                return -1;
            }

            int checkCode = getCheckCode(selectMaxCheckCodeInChecks);

            int checkCodeId = getCheckCodeId(user, checkCode, addCheckCode);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            if (checkCodeId == -1 || !saveOrder(products, insert, checkCodeId)) {
                connection.rollback();
                return -1;
            }

            connection.commit();
            return checkCode;
        } catch (SQLException e) {
            // todo log and error for client
            e.printStackTrace();
        }
        return -1;
    }

    private boolean saveOrder(List<Product> products, PreparedStatement preparedStatement, int checkCodeId) throws SQLException {
        for (Product product : products) {
            preparedStatement.setInt(1, checkCodeId);
            preparedStatement.setLong(2, product.getId());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setDouble(5, product.getBill());
            preparedStatement.addBatch();
        }

        int[] ints = preparedStatement.executeBatch();
        for (int i : ints) {
            if (i != 1) {
                return false;
            }
        }

        return true;
    }

    private int getCheckCodeId(User user, int checkCode, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, Math.toIntExact(user.getId()));
        preparedStatement.setInt(2, checkCode);
        if (preparedStatement.executeUpdate() > 0) {
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        return -1;
    }

    private int getCheckCode(Statement statement) throws SQLException {
        int checkCode = 0;
        try (ResultSet resultSet = statement.executeQuery(SELECT_MAX_CHECK_CODE)) {
            if (resultSet.next()) {
                String max_check_code = resultSet.getString("max_check_code");
                if (max_check_code != null) {
                    checkCode = Integer.parseInt(max_check_code);
                    checkCode++;
                } else {
                    checkCode++;
                }
            }
        }

        return checkCode;
    }

    private boolean stockQuantityLessThanClientOrder(List<Product> products, PreparedStatement preparedStatement) throws SQLException {
        for (Product product : products) {
            preparedStatement.setLong(1, product.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()
                        && product.getQuantity() > resultSet.getInt("quantity")) {
                    return true;
                }
            }
        }

        return false;
    }
}
