from tradingsystem import TradingSystem
def main():
    trading_system = TradingSystem()
    
    # Simulasi transaksi yang berulang
    for i in range(10000):  # Jumlah transaksi simulasi yang sangat besar
        trading_system.execute_transaction("AAPL", 10, 150.00)
    
    trading_system.print_all_transactions()

if __name__ == "__main__":
    main()