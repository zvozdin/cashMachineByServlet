package ua.com.training.dao;

import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.*;

public class StockDao {

    private static final String FIND_PAGE_PRODUCTS = "SELECT * FROM stock limit ?, ?";

    private static final String INSERT_PRODUCT = "" +
            "insert into stock(code, name, name_UA, size, quantity, price) " +
            "values (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT_QUANTITY_BY_CODE = "update stock set quantity = ? where code = ?";

    private static final String SELECT_COUNT = "select count(*) as total_rows from stock";

    public int getRowsCount() {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_COUNT)
            ) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_rows");
                }
            }
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> findAllProducts(int offset, int size) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_PRODUCTS)
        ) {
            statement.setInt(1, offset);
            statement.setInt(2, size);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(
                            new Product.ProductBuilder()
                                    .id(resultSet.getLong("id"))
                                    .code(resultSet.getString("code"))
                                    .name(resultSet.getString("name"))
                                    .name_UA(resultSet.getString("name_UA"))
                                    .size(Size.valueOf(resultSet.getString("size").toUpperCase()))
                                    .quantity(resultSet.getInt("quantity"))
                                    .price(resultSet.getDouble("price"))
                                    .build());
                }
                return products;
            }
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean save(Product product) {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)
        ) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setString(3, product.getName_UA());
            statement.setString(4, product.getSize().name());
            statement.setInt(5, product.getQuantity());
            statement.setDouble(6, product.getPrice());
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            // todo log and error for client
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateQuantityProductByCode(String code, int quantity) {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_QUANTITY_BY_CODE)
        ) {
            statement.setInt(1, quantity);
            statement.setString(2, code);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
