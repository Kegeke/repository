package ru.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnections {
    private final String url;
    private final String userName;
    private final String password;

    public DataBaseConnections() {
        ConfigLoader configLoader = new ConfigLoader();
        this.url = configLoader.getProperty("db.url");
        this.userName = configLoader.getProperty("db.username");
        this.password = configLoader.getProperty("db.password");
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
