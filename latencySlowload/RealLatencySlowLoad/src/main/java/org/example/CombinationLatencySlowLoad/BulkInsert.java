package org.example.CombinationLatencySlowLoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BulkInsert {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:test.db";
        String sqlInsert = "INSERT INTO transactions (id, amount, status, timestamp) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            conn.setAutoCommit(false); // Enable transaction

            for (int i = 1; i <= 10000; i++) {
                pstmt.setInt(1, i);
                pstmt.setInt(2, (int) (Math.random() * 1000)); // Random amount
                pstmt.setString(3, i % 2 == 0 ? "completed" : "pending"); // Alternating status
                pstmt.setString(4, "2024-08-28 12:34:56"); // Fixed timestamp for simplicity
                pstmt.addBatch();
            }

            pstmt.executeBatch(); // Execute all inserts at once
            conn.commit(); // Commit transaction
            System.out.println("Inserted 10,000 transactions successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
