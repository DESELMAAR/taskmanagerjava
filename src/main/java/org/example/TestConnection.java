package org.example;


import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("✅ Connected to MySQL successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to MySQL:");
            e.printStackTrace();
        }
    }
}
