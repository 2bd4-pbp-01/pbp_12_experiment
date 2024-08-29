#include <iostream>
#include <vector>
#include <string>

struct Transaction {
    int transactionID;
    std::string accountNumber;
    double amount;
};

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

    // Simulasi pemrosesan transaksi dalam loop
    for (int i = 0; i < 100; ++i) { // Menggunakan batasan untuk simulasi
        // Generasi ID transaksi dan detail transaksi acak
        int transactionID = rand() % 1000;
        std::string accountNumber = "ACCT" + std::to_string(rand() % 1000);
        double amount = static_cast<double>(rand() % 10000) / 100.0;
        
        std::cout << "Transaction ID: " << transactionID << std::endl;
        std::cout << "Account Number: " << accountNumber << std::endl;
        std::cout << "Amount: " << amount << std::endl;
        // Proses transaksi
        processTransaction(transactions, transactionID, accountNumber, amount);
    }

    // Pembebasan memori
    for (Transaction* tx : transactions) {
        delete tx; // Menghapus objek Transaction
    }

    return 0;
}
