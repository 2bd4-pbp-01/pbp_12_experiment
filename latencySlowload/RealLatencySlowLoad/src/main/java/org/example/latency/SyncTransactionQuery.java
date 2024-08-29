package org.example.latency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SyncTransactionQuery {

    // Simulasi query database dengan latency
    private static String getTransactionById(int id) {
        String url = "jdbc:sqlite:test.db";
        String query = "SELECT * FROM transactions WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            // Simulasi delay
            Thread.sleep(2000);
            if (rs.next()) {
                return "ID: " + rs.getInt("id") +
                       ", Amount: " + rs.getInt("amount") +
                       ", Status: " + rs.getString("status") +
                       ", Timestamp: " + rs.getString("timestamp");
            } else {
                return "Transaction with ID " + id + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred.";
        }
    }

    public static void main(String[] args) {
        // Mulai waktu
        long startTime = System.currentTimeMillis();

        // Query transaksi secara sinkron
        String tx1 = getTransactionById(1);
        String tx2 = getTransactionById(2);
        String tx3 = getTransactionById(3);

        // Menampilkan hasil
        System.out.println("Results:");
        System.out.println(tx1);
        System.out.println(tx2);
        System.out.println(tx3);

        // Akhir waktu
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}

