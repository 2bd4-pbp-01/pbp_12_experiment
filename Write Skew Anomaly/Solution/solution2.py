import threading

# Saldo awal rekening tabungan
account_balances = {
    "Nasabah A": 600,
    "Nasabah B": 600
}

def transfer_to_investment_optimistic(customer_name, transfer_amount):
    global account_balances
    
    while True:
        with lock:
            total_balance = sum(account_balances.values())
            print(f"{customer_name} melihat saldo gabungan: ${total_balance}")

            # Jika kondisi masih sesuai, lanjutkan transfer
            if total_balance - transfer_amount >= total_minimum_balance:
                # Verifikasi bahwa saldo belum berubah sebelum menulis
                new_total_balance = sum(account_balances.values())
                if new_total_balance == total_balance:
                    account_balances[customer_name] -= transfer_amount
                    print(f"{customer_name} mentransfer ${transfer_amount} ke rekening investasi.")
                    break
                else:
                    print(f"{customer_name} mendeteksi perubahan, mencoba lagi...")
            else:
                print(f"{customer_name} tidak bisa mentransfer ${transfer_amount} karena saldo gabungan akan kurang dari ${total_minimum_balance}.")
                break

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
thread1 = threading.Thread(target=transfer_to_investment_optimistic, args=("Nasabah A", 600))
thread2 = threading.Thread(target=transfer_to_investment_optimistic, args=("Nasabah B", 600))

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(f"Saldo akhir rekening tabungan: {account_balances}")

