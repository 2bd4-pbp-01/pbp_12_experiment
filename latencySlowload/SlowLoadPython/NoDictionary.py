import asyncio

async def make_coffee(order):
    print(f"Starting to make {order}...")
    await asyncio.sleep(5)  # Simulasi pembuatan kopi yang memerlukan waktu 5 detik
    coffee = f"Freshly made {order}"
    print(f"{order} is ready!")
    return coffee

async def main():
    orders = ["Latte", "Espresso", "Latte"]  # Pesanan berulang

    for order in orders:
        await make_coffee(order)

asyncio.run(main())
