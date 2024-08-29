package org.example.latency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:test.db";
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY, " +
                "amount INTEGER, " +
                "status TEXT, " +
                "timestamp TEXT)";
        String sqlInsertData = "INSERT INTO transactions (id, amount, status, timestamp) VALUES " +
                "(1, 100, 'completed', '2024-08-28 12:34:56'), " +
                "(2, 200, 'pending', '2024-08-28 13:45:00'), " +
                "(3, 150, 'failed', '2024-08-28 14:55:12')";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // Buat tabel
            stmt.execute(sqlCreateTable);
            // Tambah data
            stmt.execute(sqlInsertData);
            System.out.println("Database and initial data created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
