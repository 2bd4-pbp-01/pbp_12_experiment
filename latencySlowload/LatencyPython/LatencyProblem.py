import asyncio


async def process_async_task():
    print("Task started...")
    await asyncio.sleep(5)  # Simulasi tugas berat dengan penundaan 5 detik
    print("Asynchronous Task Completed!")
    return "Task Completed"


async def main():
    print("Starting task...")

    # Menjalankan tugas asinkron untuk menghindari latency
    future = asyncio.create_task(process_async_task())

    # Lakukan tugas lain sementara menunggu future selesai
    print("Doing other work while waiting...")

    result = await future  # Menunggu hasil dari tugas asinkron
    print(f"Result: {result}")


# Menjalankan loop asyncio
asyncio.run(main())
