package org.example.CombinationLatencySlowLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComplexTransactionManager {
    private static TransactionCache cache = TransactionCache.getInstance();

    public static void main(String[] args) {
        // Mulai waktu untuk keseluruhan proses
        long overallStartTime = System.currentTimeMillis();

        // Tugas CompletableFuture untuk mengambil semua transaksi
        CompletableFuture<List<Transaction>> futureTransactions = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            List<Transaction> transactions = cache.getAllTransactions();
            long endTime = System.currentTimeMillis();
            System.out.println("Waktu yang dibutuhkan untuk mengambil semua transaksi: " + (endTime - startTime) + " ms");
            return transactions;
        });

        // Tangani hasil setelah semua query selesai
        futureTransactions.thenRun(() -> {
            try {
                List<Transaction> transactions = futureTransactions.get();
                System.out.println("Hasil:");
                transactions.forEach(System.out::println);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // Tunggu sampai semua tugas asinkron selesai
        try {
            futureTransactions.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Akhir waktu untuk keseluruhan proses
        long overallEndTime = System.currentTimeMillis();
        System.out.println("Total waktu yang dibutuhkan: " + (overallEndTime - overallStartTime) + " ms");
    }
}
