package Latency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class LatencyExample {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static CompletableFuture<String> processAsyncTask() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulasi tugas berat
                Thread.sleep(5000);
                System.out.println("Asynchronous Task Completed!");
                return "Task Completed";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Task Failed";
            }
        }, executor);
    }

    public static void main(String[] args) {
        System.out.println("Starting task...");
        CompletableFuture<String> future = processAsyncTask();
        future.thenAccept(result -> System.out.println("Result: " + result));
        // Tunggu beberapa detik untuk memastikan tugas selesai
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}

