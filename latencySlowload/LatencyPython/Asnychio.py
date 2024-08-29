import asyncio


async def make_coffee(order):
    print(f"Starting to make {order}...")
    await asyncio.sleep(5)  # Simulasi pembuatan kopi yang memerlukan waktu 5 detik
    print(f"{order} is ready!")


async def main():
    orders = ["Latte", "Espresso", "Cappuccino"]

    # Membuat beberapa kopi secara bersamaan
    tasks = [make_coffee(order) for order in orders]
    await asyncio.gather(*tasks)  # Menjalankan semua tugas secara bersamaan


asyncio.run(main())
