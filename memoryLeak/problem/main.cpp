#include <iostream>
#include <vector>
#include <string>

// Struktur data untuk menyimpan informasi transaksi
struct Transaction {
    int transactionID;
    std::string accountNumber;
    double amount;
};

// Fungsi untuk memproses transaksi
void processTransaction(std::vector<Transaction*>& transactions, int transactionID, const std::string& accountNumber, double amount) {
    // Alokasi memori untuk objek Transaction
    Transaction* newTransaction = new Transaction();
    newTransaction->transactionID = transactionID;
    newTransaction->accountNumber = accountNumber;
    newTransaction->amount = amount;
    
    // Menambahkan transaksi ke dalam vektor
    transactions.push_back(newTransaction);
}

int main() {
    std::vector<Transaction*> transactions;

    // Simulasi pemrosesan transaksi dalam loop tanpa batas
    while (true) {
        // Generasi ID transaksi dan detail transaksi acak
        int transactionID = rand() % 1000;
        std::string accountNumber = "ACCT" + std::to_string(rand() % 1000);
        double amount = static_cast<double>(rand() % 10000) / 100.0;
        
        // Proses transaksi
        processTransaction(transactions, transactionID, accountNumber, amount);

        // (Misalnya) Ada delay atau pemrosesan lainnya di sini
        // sleep(1); // Hanya untuk memberikan gambaran, namun tidak digunakan dalam contoh ini
    }

    // Jangan pernah sampai ke sini karena loop tak terbatas
    // Namun jika sampai di sini, memori yang telah dialokasikan tidak pernah dibebaskan
    // Oleh karena itu, memory leak terjadi
    return 0;
}
