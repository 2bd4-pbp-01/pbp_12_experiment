import asyncio

cache = {}  # Dictionary untuk menyimpan data yang sudah diambil sebelumnya


async def get_data(key):
    # Cek apakah data sudah ada di cache
    if key in cache:
        print(f"Cache hit for key: {key}")
        return cache[key]

    # Simulasi tugas berat (misalnya, mengambil data dari database atau API)
    print(f"Fetching data for key: {key}")
    await asyncio.sleep(2)  # Simulasi penundaan 2 detik

    # Simpan hasilnya di cache
    data = f"Data for {key}"
    cache[key] = data
    print(f"Data processed and cached for key: {key}")
    return data


async def main():
    # Ambil data untuk key "item1"
    print("Fetching data for item1...")
    result1 = await get_data("item1")
    print(f"Result: {result1}")

    # Tunggu beberapa detik sebelum mengambil data lagi untuk key yang sama
    await asyncio.sleep(3)

    # Ambil data lagi untuk key "item1", harusnya menggunakan cache
    print("Fetching data for item1 again...")
    result2 = await get_data("item1")
    print(f"Result: {result2}")


# Menjalankan loop asyncio
asyncio.run(main())
