import time


def make_coffee(order):
    print(f"Starting to make {order}...")
    time.sleep(5)  # Simulasi pembuatan kopi yang memerlukan waktu 5 detik
    print(f"{order} is ready!")


def main():
    orders = ["Latte", "Espresso", "Cappuccino"]

    for order in orders:
        make_coffee(order)


if __name__ == "__main__":
    main()
