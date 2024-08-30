package org.example.CombinationLatencySlowLoad;

public class Transaction {
    private int id;
    private int amount;
    private String status;
    private String timestamp;

    public Transaction(int id, int amount, String status, String timestamp) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{id=" + id + 
               ", amount=" + amount + 
               ", status='" + status + '\'' + 
               ", timestamp='" + timestamp + '\'' + 
               '}';
    }
}
