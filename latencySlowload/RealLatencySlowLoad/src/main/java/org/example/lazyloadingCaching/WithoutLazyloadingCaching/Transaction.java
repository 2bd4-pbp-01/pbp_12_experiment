package org.example.lazyloadingCaching.WithoutLazyloadingCaching;

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

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

