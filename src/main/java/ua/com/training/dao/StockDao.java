package ua.com.training.dao;

import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.*;

public class StockDao extends Dao {

    private static final String TABLE_NAME = "stock";
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM stock";
    private static final String INSERT_PRODUCT = "" +
            "insert into stock(code, name, size, quantity, price) " +
            "values (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUANTITY_BY_CODE = "update stock set quantity=? where code=?";

    public Set<String> getColumns() {
        return getColumns(TABLE_NAME);
    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCTS)) {
                while (resultSet.next()) {
                    products.add(
                            new Product.ProductBuilder()
                                    .id(resultSet.getLong("id"))
                                    .code(resultSet.getString("code"))
                                    .name(resultSet.getString("name"))
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
            statement.setString(3, product.getSize().name());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());

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
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
