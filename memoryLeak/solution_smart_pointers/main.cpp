#include <iostream>
#include <vector>
#include <memory> // Untuk smart pointers
#include <string>

struct Transaction {
    int transactionID;
    std::string accountNumber;
    double amount;
};

void processTransaction(std::vector<std::unique_ptr<Transaction>>& transactions, int transactionID, const std::string& accountNumber, double amount) {
    // Alokasi memori untuk objek Transaction menggunakan unique_ptr
    auto newTransaction = std::make_unique<Transaction>();
    newTransaction->transactionID = transactionID;
    newTransaction->accountNumber = accountNumber;
    newTransaction->amount = amount;
    
    // Menambahkan transaksi ke dalam vektor
    transactions.push_back(std::move(newTransaction));
}

int main() {
    std::vector<std::unique_ptr<Transaction>> transactions;

    // Simulasi pemrosesan transaksi dalam loop
    for (int i = 0; i < 100; ++i) { // Menggunakan batasan untuk simulasi
        // Generasi ID transaksi dan detail transaksi acak
        int transactionID = rand() % 1000;
        std::string accountNumber = "ACCT" + std::to_string(rand() % 1000);
        double amount = static_cast<double>(rand() % 10000) / 100.0;
        
        // Proses transaksi
        processTransaction(transactions, transactionID, accountNumber, amount);
    }

    // Tidak perlu pembebasan memori eksplisit, unique_ptr otomatis mengelola memori
    return 0;
}
