package org.example.lazyloadingCaching;

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
        
        // Membuat data dengan lebih banyak entri
        StringBuilder sqlInsertData = new StringBuilder("INSERT INTO transactions (id, amount, status, timestamp) VALUES ");
        for (int i = 1; i <= 10000; i++) {
            sqlInsertData.append(String.format("(%d, %d, '%s', '2024-08-28 %02d:%02d:%02d')%s", 
                i, 
                (i * 10) % 500, 
                i % 2 == 0 ? "completed" : "pending", 
                (i / 60) % 24, 
                i % 60, 
                i % 60, 
                i < 10000 ? ", " : ""
            ));
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // Buat tabel
            stmt.execute(sqlCreateTable);
            // Tambah data
            stmt.execute(sqlInsertData.toString());
            System.out.println("Database and initial data created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
