package org.example.SimplifiedCase.Problem;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatencySlowLoad {
     private static final int MAX_REQUESTS = 1000; // Jumlah maksimum permintaan
    private static ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread pool dengan jumlah terbatas

    public static void main(String[] args) {
        System.out.println("Starting e-commerce simulation...");

        long startTime = System.currentTimeMillis(); // Catat waktu mulai

        for (int i = 1; i <= MAX_REQUESTS; i++) {
            int requestId = i;
            CompletableFuture.runAsync(() -> handleRequest(requestId), executorService)
                .exceptionally(ex -> {
                    System.out.println("Error handling request " + requestId + ": " + ex.getMessage());
                    return null;
                });
        }

        executorService.shutdown();

        long endTime = System.currentTimeMillis(); // Catat waktu akhir
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }

    private static void handleRequest(int requestId) {
        long requestStartTime = System.currentTimeMillis(); // Catat waktu mulai permintaan

        // Simulasi penundaan yang terjadi akibat latensi server
        try {
            System.out.println("Processing request " + requestId + "...");
            Thread.sleep(5000); // Simulasi delay 5 detik (latensi)
            System.out.println("Request " + requestId + " completed.");
        } catch (InterruptedException e) {
            System.out.println("Request " + requestId + " failed due to timeout.");
        }

        long requestEndTime = System.currentTimeMillis(); // Catat waktu akhir permintaan
        System.out.println("Request " + requestId + " time: " + (requestEndTime - requestStartTime) + " ms");
    }
}
