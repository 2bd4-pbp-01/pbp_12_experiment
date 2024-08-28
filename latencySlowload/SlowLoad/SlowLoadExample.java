package SlowLoad;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SlowLoadExample {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static CompletableFuture<String> getData(String key) {
        return CompletableFuture.supplyAsync(() -> {
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(20000); // Simulasi 5 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String data = "Data for " + key;
            long end = System.currentTimeMillis();
            System.out.println("Data processed for key: " + key + " in " + (end - start) + " ms");
            return data;
        }, executor);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Fetching data for key1...");
        CompletableFuture<String> future1 = getData("key1");
        future1.thenAccept(result -> System.out.println("Result: " + result));

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fetching data for key1 again...");
        CompletableFuture<String> future2 = getData("key1");
        future2.thenAccept(result -> System.out.println("Result: " + result));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start) + " ms");
        executor.shutdown();
    }
}
