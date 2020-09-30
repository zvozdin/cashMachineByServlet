package ua.com.training.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Dao {

    public Set<String> getColumns(String tableName) {
        Set<String> result = new LinkedHashSet<>();
        try (Connection connection = DatabaseConnectionPool.getConnection()) {
            DatabaseMetaData data = connection.getMetaData();
            try (ResultSet columnsNames = data.getColumns(
                    null, null, /*"%"*/tableName, null)) {
                while (columnsNames.next()) {
                    result.add(columnsNames.getString(4));
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }
}