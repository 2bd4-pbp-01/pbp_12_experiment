package org.example.lazyloadingCaching.WithoutLazyloadingCaching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDao {
    private static final String URL = "jdbc:sqlite:test.db";

    // Mengambil transaksi berdasarkan ID dari database
    public Transaction getTransaction(int id) {
        String sql = "SELECT id, amount, status, timestamp FROM transactions WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Transaction(
                        rs.getInt("id"),
                        rs.getInt("amount"),
                        rs.getString("status"),
                        rs.getString("timestamp")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
