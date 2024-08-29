from transaction import Transaction
class TradingSystem:
    def __init__(self, max_transactions=1000):
        self.transactions = []
        self.max_transactions = max_transactions

    def execute_transaction(self, symbol, quantity, price):
        transaction = Transaction(symbol, quantity, price)
        if len(self.transactions) >= self.max_transactions:
            self.transactions.pop(0)  # Menghapus transaksi paling lama
        self.transactions.append(transaction)
        self._process_transaction(transaction)

    def _process_transaction(self, transaction):
        # Simulasi pemrosesan transaksi
        print(f"Processing {transaction}")
        
    def print_all_transactions(self):
        for transaction in self.transactions:
            print(transaction)
