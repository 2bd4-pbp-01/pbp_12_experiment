import asyncio

# Dictionary untuk menyimpan kopi yang sudah disiapkan
cache = {}

async def make_coffee(order):
    if order in cache:
        print(f"Serving {order} from cache!")
        return cache[order]

    print(f"Starting to make {order}...")
    await asyncio.sleep(5)  # Simulasi pembuatan kopi yang memerlukan waktu 5 detik
    coffee = f"Freshly made {order}"
    cache[order] = coffee
    print(f"{order} is ready and added to cache!")
    return coffee

async def main():
    orders = ["Latte", "Espresso", "Latte"]  # Pesanan berulang

    for order in orders:
        await make_coffee(order)

asyncio.run(main())
