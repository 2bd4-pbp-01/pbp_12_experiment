package org.example.lazyloadingCaching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class TransactionCache {
    private static TransactionCache instance;
    private Map<Integer, Transaction> cache = new HashMap<>();
    private final String URL = "jdbc:sqlite:test.db";

    private TransactionCache() {
    }

    public static synchronized TransactionCache getInstance() {
        if (instance == null) {
            instance = new TransactionCache();
        }
        return instance;
    }

    public Transaction getTransaction(int id) {
        if (cache.containsKey(id)) {
            System.out.println("Fetching from cache...");
            return cache.get(id);
        }

        System.out.println("Loading from database...");
        String sql = "SELECT id, amount, status, timestamp FROM transactions WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("amount"),
                        rs.getString("status"),
                        rs.getString("timestamp")
                    );
                    cache.put(id, transaction); // Cache the transaction
                    return transaction;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}

