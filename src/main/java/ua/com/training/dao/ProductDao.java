package ua.com.training.dao;

import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public static final String FIND_ALL_PRODUCTS = "SELECT * FROM products";
    public static final String INSERT_PRODUCT = "insert into products(code, name, size, quantity, price, expert_id) values (?, ?, ?, ?, ?, ?)";

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