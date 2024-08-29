import time

class TransactionHistoryLazyCache:
    def __init__(self):
        self._transaction_history = None

    def _load_transaction_history(self):
        print("Loading transaction history...")
        time.sleep(5)  # Simulasi waktu muat 5 detik
        self._transaction_history = [f"Transaction {i}" for i in range(1, 10001)]
        print("Transaction history loaded and cached.")

    @property
    def transaction_history(self):
        if self._transaction_history is None:
            self._load_transaction_history()
        else:
            print("Using cached transaction history.")
        return self._transaction_history

# Penggunaan
if __name__ == "__main__":
    history = TransactionHistoryLazyCache()

    # Aplikasi bisa memulai dengan cepat tanpa menunggu data riwayat transaksi
    print("Application started.")

    # Saat riwayat transaksi dibutuhkan, baru data dimuat
    transactions = history.transaction_history
    print("Transaction history ready to display.")

    # Coba akses lagi, kali ini menggunakan cache
    transactions = history.transaction_history
    print("Transaction history displayed again using cache.")
