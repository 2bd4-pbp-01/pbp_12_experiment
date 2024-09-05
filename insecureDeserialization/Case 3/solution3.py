import pickle
import io

# Hanya tipe-tipe objek yang aman yang diperbolehkan
class SafeUnpickler(pickle.Unpickler):
    def find_class(self, module, name):
        # Batasi hanya tipe tertentu yang diizinkan
        if module == "__main__" and name in ["InnocentClass"]:
            return super().find_class(module, name)
        raise pickle.UnpicklingError(f"Global '{module}.{name}' is not allowed")

class InnocentClass:
    def __init__(self, data):
        self.data = data

    def display(self):
        print(f"Data: {self.data}")

# Simulasi deserialisasi yang aman
def safe_loads(data):
    return SafeUnpickler(io.BytesIO(data)).load()

# Serialize objek yang aman
innocent_object = InnocentClass("This is safe data")
serialized_data_safe = pickle.dumps(innocent_object)

# Coba deserialisasi objek yang aman
print("Deserializing safe object...")
try:
    deserialized_object = safe_loads(serialized_data_safe)
    deserialized_object.display()
except pickle.UnpicklingError as e:
    print(f"Deserialization error: {e}")
    

# Serialize objek yang terlihat tidak berbahaya
serialized_data = pickle.dumps(innocent_object)

# Coba deserialisasi objek yang berbahaya (harus menyebabkan error)
print("Deserializing malicious object...")
try:
    safe_loads(serialized_data)  # Ini akan menyebabkan error karena whitelisting
except pickle.UnpicklingError as e:
    print(f"Deserialization error: {e}")
