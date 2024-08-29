import threading

# Saldo awal rekening tabungan
account_balances = {
    "Nasabah A": 600,
    "Nasabah B": 600
}
total_minimum_balance = 1000
lock = threading.Lock()

def transfer_to_investment(customer_name, transfer_amount):
    global account_balances
    
    with lock:
        # Cek saldo gabungan dari semua rekening tabungan
        total_balance = sum(account_balances.values())
        print(f"{customer_name} melihat saldo gabungan: ${total_balance}")

        # Lakukan transfer
        account_balances[customer_name] -= transfer_amount
        print(f"{customer_name} mentransfer ${transfer_amount} ke rekening investasi.")

# Simulasi Write Skew Anomaly
thread1 = threading.Thread(target=transfer_to_investment, args=("Nasabah A", 600))
thread2 = threading.Thread(target=transfer_to_investment, args=("Nasabah B", 600))

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(f"Saldo akhir rekening tabungan: {account_balances}")
