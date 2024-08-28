package SlowLoad;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryLazyCache {

    private List<String> transactionHistory;

    // Lazy Loading: Inisialisasi hanya jika diperlukan
    public List<String> getTransactionHistory() {
        if (transactionHistory == null) {
            System.out.println("Loading transaction history...");
            loadTransactionHistory();
        } else {
            System.out.println("Using cached transaction history.");
        }
        return transactionHistory;
    }

    // Simulasi pengambilan data besar dari database
    private void loadTransactionHistory() {
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
        System.out.println("Transaction history loaded and cached.");
    }

    public static void main(String[] args) {
        TransactionHistoryLazyCache history = new TransactionHistoryLazyCache();

        // Aplikasi bisa memulai dengan cepat tanpa menunggu data riwayat transaksi
        System.out.println("Application started.");

        // Saat riwayat transaksi dibutuhkan, baru data dimuat
        List<String> transactions = history.getTransactionHistory();
        System.out.println("Transaction history ready to display.");

        // Coba akses lagi, kali ini menggunakan cache
        transactions = history.getTransactionHistory();
        System.out.println("Transaction history displayed again using cache.");
    }
}
