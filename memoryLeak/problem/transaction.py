class Transaction:
    def __init__(self, symbol, quantity, price):
        self.symbol = symbol
        self.quantity = quantity
        self.price = price

    def __repr__(self):
        return f"Transaction(symbol={self.symbol}, quantity={self.quantity}, price={self.price})"