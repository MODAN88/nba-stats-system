package com.skyhawk.nba.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://postgres:5432/nba_stats",
                    "user",
                    "password");
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}