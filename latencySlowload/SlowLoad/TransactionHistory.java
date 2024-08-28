package SlowLoad;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {

    private List<String> transactionHistory;

    // Simulasi pengambilan data besar dari database
    public void loadTransactionHistory() {
        transactionHistory = new ArrayList<>();
        try {
            // Simulasi waktu lama untuk mengambil data dari database
            Thread.sleep(5000);  // Simulasi waktu muat 5 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Isi daftar transaksi dengan data simulasi
        for (int i = 1; i <= 10000; i++) {
            transactionHistory.add("Transaction " + i);
        }
        System.out.println("Transaction history loaded.");
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public static void main(String[] args) {
        TransactionHistory history = new TransactionHistory();

        System.out.println("Loading transaction history...");
        history.loadTransactionHistory();  // Ini menyebabkan slow load
        System.out.println("Transaction history loaded and ready to display.");
    }
}
