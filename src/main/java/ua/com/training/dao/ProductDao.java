package ua.com.training.dao;

import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.*;

public class ProductDao extends Dao {

    private static final String TABLE_NAME = "products";
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String INSERT_PRODUCT = "insert into products(code, name, size, quantity, price, expert_id) values (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUANTITY_BY_CODE = "update products set quantity=? where code=?";

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
                    Product product = mapProductFromResultSet(resultSet);
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException e) {
            // todo log exception
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public boolean addProduct(Product product) {
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)
        ) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setString(3, product.getSize().name());
            statement.setInt(4, product.getQuantity());
            statement.setDouble(5, product.getPrice());
            statement.setLong(6, product.getCommodityExpertId());

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
        Connection connection = null;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_QUANTITY_BY_CODE)
        ) {
            connection = DatabaseConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement.setInt(1, quantity);
            statement.setString(2, code);

            if (statement.executeUpdate() > 0) {
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            }
        } catch (SQLException e) {
            // todo log and error for client
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e) {
                // todo log and error for client
                e.printStackTrace();
            }
            return false;
        }
    }

    private Product mapProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setCode(resultSet.getString("code"));
        product.setName(resultSet.getString("name"));
        product.setSize(Size.valueOf(resultSet.getString("size").toUpperCase()));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setPrice(resultSet.getDouble("price"));
        product.setCommodityExpertId(resultSet.getLong("expert_id"));
        return product;
    }
}