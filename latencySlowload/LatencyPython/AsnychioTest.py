import asyncio

async def validate_credit_card(card_number):
    print("Validating credit card...")
    await asyncio.sleep(2)  # Simulasi operasi yang memakan waktu lama
    return True  # Asumsikan validasi sukses

async def check_balance(account_id):
    print("Checking balance...")
    await asyncio.sleep(1.5)  # Simulasi operasi yang memakan waktu lama
    return True  # Asumsikan saldo mencukupi

async def complete_transaction(transaction_id):
    print("Completing transaction...")
    await asyncio.sleep(1)  # Simulasi operasi yang memakan waktu lama
    return True  # Asumsikan transaksi berhasil

async def process_transaction(card_number, account_id, transaction_id):
    print("Starting transaction processing...")

    is_card_valid = await validate_credit_card(card_number)
    if not is_card_valid:
        print("Transaction failed: Invalid credit card.")
        return

    is_balance_sufficient = await check_balance(account_id)
    if not is_balance_sufficient:
        print("Transaction failed: Insufficient balance.")
        return

    is_transaction_complete = await complete_transaction(transaction_id)
    if is_transaction_complete:
        print("Transaction completed successfully.")
    else:
        print("Transaction failed during completion.")

    print("Transaction processing finished.")

if __name__ == "__main__":
    card_number = "1234-5678-9012-3456"
    account_id = "account123"
    transaction_id = "trans001"

    # Menjalankan fungsi asinkron
    asyncio.run(process_transaction(card_number, account_id, transaction_id))
