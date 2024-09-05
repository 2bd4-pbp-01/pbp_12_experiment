import threading

# Saldo awal rekening tabungan
account_balances = {
    "Nasabah A": 600,
    "Nasabah B": 600
}
total_minimum_balance = 1000
lock = threading.Lock()

def transfer_to_investment_pessimistic(customer_name, transfer_amount):
    global account_balances
    
    with lock:
        # Cek saldo gabungan dari semua rekening tabungan
        total_balance = sum(account_balances.values())
        print(f"{customer_name} melihat saldo gabungan: ${total_balance}")
        6
        # Jika saldo gabungan di atas minimum, lakukan transfer
        if total_balance - transfer_amount >= total_minimum_balance:
            account_balances[customer_name] -= transfer_amount
            print(f"{customer_name} mentransfer ${transfer_amount} ke rekening investasi.")
        else:
            print(f"{customer_name} tidak bisa mentransfer ${transfer_amount} karena saldo gabungan akan kurang dari ${total_minimum_balance}.")

# Simulasi Write Skew Anomaly menggunakan pendekatan pesimistis
thread1 = threading.Thread(target=transfer_to_investment_pessimistic, args=("Nasabah A", 600))
thread2 = threading.Thread(target=transfer_to_investment_pessimistic, args=("Nasabah B", 600))

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(f"Saldo akhir rekening tabungan: {account_balances}")
