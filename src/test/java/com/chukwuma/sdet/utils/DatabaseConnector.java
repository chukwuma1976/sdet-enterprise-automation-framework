package com.chukwuma.sdet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chukwuma.sdet.tests.database.DatabaseTestContainer;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseTestContainer.getJdbcUrl(),
                DatabaseTestContainer.getUsername(),
                DatabaseTestContainer.getPassword());
    }
}