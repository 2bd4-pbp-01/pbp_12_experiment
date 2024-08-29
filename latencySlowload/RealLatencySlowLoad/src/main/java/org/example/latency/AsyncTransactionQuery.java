package org.example.latency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AsyncTransactionQuery {

    // Simulasi query database dengan latency
    private static CompletableFuture<String> getTransactionById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "jdbc:sqlite:test.db";
            String query = "SELECT * FROM transactions WHERE id = " + id;
            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                // Simulasi delay
                TimeUnit.SECONDS.sleep(2);
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
        });
    }

    public static void main(String[] args) {
        // Mulai waktu
        long startTime = System.currentTimeMillis();

        // Query transaksi secara asinkron
        CompletableFuture<String> tx1 = getTransactionById(1);
        CompletableFuture<String> tx2 = getTransactionById(2);
        CompletableFuture<String> tx3 = getTransactionById(3);

        // Tunggu sampai semua query selesai
        CompletableFuture<Void> allOf = CompletableFuture.allOf(tx1, tx2, tx3);

        // Menangani hasil setelah semua query selesai
        allOf.thenRun(() -> {
            try {
                String t1 = tx1.get();
                String t2 = tx2.get();
                String t3 = tx3.get();
                System.out.println("Results:");
                System.out.println(t1);
                System.out.println(t2);
                System.out.println(t3);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // Tunggu sampai semua query selesai sebelum menghentikan aplikasi
        try {
            allOf.get(); // Tunggu sampai semua tugas selesai
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Akhir waktu
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}

