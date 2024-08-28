package Latency;

public class LatencyProblem {

    public static String processTask() {
        try {
            // Simulasi tugas berat
            Thread.sleep(5000);
            System.out.println("Synchronous Task Completed!");
            return "Task Completed";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Task Failed";
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting task...");
        String result = processTask();
        System.out.println("Result: " + result);
    }
}

