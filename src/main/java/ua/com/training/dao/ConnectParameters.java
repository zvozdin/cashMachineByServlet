package ua.com.training.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectParameters {

    static String database;
    static String user;
    static String password;
    static String driver;
    static String url;
    static String ssl;
    static String useUnicode;
    static String encoding;
    static Integer minIdle;
    static Integer maxIdle;
    static Integer maxOpenPStatements;

    // todo implement HikariCP lib as most popular https://www.baeldung.com/hikaricp
    // todo Однако в реальной среде Java EE вы должны делегировать создание DataSource контейнеру / серверу приложений
    // и получить его от JNDI. В случае Tomcat см. Также, например,
    // этот документ: http://tomcat.apache.org/tomcat-6.0-doc/jndi-resources-howto.html

    public static void get() {
        try (InputStream propertiesInputStream = ConnectParameters.class
                    .getClassLoader().getResourceAsStream("db.properties")) {

            Properties properties = new Properties();
            properties.load(propertiesInputStream);
            database = properties.getProperty("databaseName");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driverMySQL");
            url = properties.getProperty("url");
            ssl = properties.getProperty("establishingSSL");
            useUnicode = properties.getProperty("useUnicode");
            encoding = properties.getProperty("encoding");
            minIdle = Integer.parseInt(properties.getProperty("minIdle"));
            maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
            maxOpenPStatements = Integer.parseInt(properties.getProperty("maxOpenPreparedStatements"));
        } catch (IOException e) {
            throw new RuntimeException("can't set properties for database");
        }
    }
}