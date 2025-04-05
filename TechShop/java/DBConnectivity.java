package com.AssignmentTechshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/TechShop"; // Replace with your DB name
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "1234"; // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
