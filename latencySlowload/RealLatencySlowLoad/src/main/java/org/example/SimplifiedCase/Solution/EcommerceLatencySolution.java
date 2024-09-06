package org.example.SimplifiedCase.Solution;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class EcommerceLatencySolution {

    private static final int MAX_REQUESTS = 1000; 
    private static ExecutorService executorService = Executors.newCachedThreadPool(); // Thread pool dinamis
    private static HashMap<Integer, String> cache = new HashMap<>(); // Simulasi cache

    public static void main(String[] args) {
        System.out.println("Starting e-commerce simulation with latency solution...");

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
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        long endTime = System.currentTimeMillis(); // Catat waktu akhir
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }

    private static void handleRequest(int requestId) {
        long requestStartTime = System.currentTimeMillis(); // Catat waktu mulai permintaan

        // Simulasi caching: Cek apakah data sudah ada di cache
        if (cache.containsKey(requestId)) {
            System.out.println("Request " + requestId + " served from cache.");
        } else {
            try {
                System.out.println("Processing request " + requestId + " with optimized latency...");
                Thread.sleep(1000); // Latency dikurangi dari 5 detik menjadi 1 detik
                cache.put(requestId, "Response data for request " + requestId); // Simpan hasil di cache
                System.out.println("Request " + requestId + " completed.");
            } catch (InterruptedException e) {
                System.out.println("Request " + requestId + " failed due to timeout.");
            }
        }

        long requestEndTime = System.currentTimeMillis(); // Catat waktu akhir permintaan
        System.out.println("Request " + requestId + " time: " + (requestEndTime - requestStartTime) + " ms");
    }
}
