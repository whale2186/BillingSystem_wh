package org.billingwh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // IMPORTANT: Replace with your MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/billing_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure it's in your project's classpath.");
            throw new SQLException("JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}