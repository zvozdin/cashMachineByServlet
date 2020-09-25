package ua.com.training.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseConnectionPool {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        ConnectParameters.get();
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(ConnectParameters.driver);
        dataSource.setUsername(ConnectParameters.user);
        dataSource.setPassword(ConnectParameters.password);
        dataSource.setUrl(createFullUrlFromPropertiesForDB());
        dataSource.setMinIdle(ConnectParameters.minIdle);
        dataSource.setMaxIdle(ConnectParameters.maxIdle);
        dataSource.setMaxOpenPreparedStatements(ConnectParameters.maxOpenPStatements);
    }

    private DatabaseConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static String createFullUrlFromPropertiesForDB() {
        return new StringBuilder()
                .append(ConnectParameters.url)
                .append("/")
                .append(ConnectParameters.database)
                .append("?")
                .append(ConnectParameters.ssl)
                .append("&")
                .append(ConnectParameters.useUnicode)
                .append("&")
                .append(ConnectParameters.encoding)
                .toString();
    }
}