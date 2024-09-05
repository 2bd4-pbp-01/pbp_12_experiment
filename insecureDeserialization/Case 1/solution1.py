import json

class InnocentClass:
    def __init__(self, data):
        self.data = data

    def display(self):
        print(f"Data: {self.data}")

    def to_dict(self):
        return {'data': self.data}

    @staticmethod
    def from_dict(data):
        return InnocentClass(data['data'])

# Serialize objek menggunakan JSON
innocent_object = InnocentClass("This is safe data")
serialized_data_safe = json.dumps(innocent_object.to_dict())

# Deserialisasi yang aman
print("Deserializing safe object...")
deserialized_data = json.loads(serialized_data_safe)
deserialized_object = InnocentClass.from_dict(deserialized_data)
deserialized_object.display()
