from transaction import Transaction
class TradingSystem:
    def __init__(self):
        self.transactions = []

    def execute_transaction(self, symbol, quantity, price):
        transaction = Transaction(symbol, quantity, price)
        self.transactions.append(transaction)
        # Simulasi proses transaksi
        self._process_transaction(transaction)

    def _process_transaction(self, transaction):
        # Simulasi pemrosesan transaksi
        print(f"Processing {transaction}")

    def print_all_transactions(self):
        for transaction in self.transactions:
            print(transaction)