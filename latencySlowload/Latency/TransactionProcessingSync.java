package Latency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessingSync {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static CompletableFuture<Boolean> validateCreditCard(String cardNumber) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulasi validasi kartu kredit (misalnya, panggilan API)
                System.out.println("Validating credit card...");
                Thread.sleep(2000);
                return true; // Asumsikan validasi sukses
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> checkBalance(String accountId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulasi pengecekan saldo (misalnya, akses database)
                System.out.println("Checking balance...");
                Thread.sleep(1500);
                return true; // Asumsikan saldo mencukupi
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> completeTransaction(String transactionId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulasi penyelesaian transaksi (misalnya, simpan ke database)
                System.out.println("Completing transaction...");
                Thread.sleep(1000);
                return true; // Asumsikan transaksi berhasil
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }, executor);
    }

    public static void processTransaction(String cardNumber, String accountId, String transactionId) {
        System.out.println("Starting transaction processing...");

        CompletableFuture<Boolean> validationFuture = validateCreditCard(cardNumber);
        CompletableFuture<Boolean> balanceCheckFuture = checkBalance(accountId);

        // Menunggu validasi kartu kredit dan pengecekan saldo selesai sebelum menyelesaikan transaksi
        CompletableFuture<Void> transactionFuture = CompletableFuture.allOf(validationFuture, balanceCheckFuture)
                .thenCompose(v -> completeTransaction(transactionId))
                .thenAccept(result -> {
                    if (result) {
                        System.out.println("Transaction completed successfully.");
                    } else {
                        System.out.println("Transaction failed.");
                    }
                });

        // Tunggu beberapa detik agar semua tugas selesai
        try {
            transactionFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Transaction processing finished.");
    }

    public static void main(String[] args) {
        String cardNumber = "1234-5678-9012-3456";
        String accountId = "account123";
        String transactionId = "trans001";

        processTransaction(cardNumber, accountId, transactionId);

        // Shutdown executor
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


