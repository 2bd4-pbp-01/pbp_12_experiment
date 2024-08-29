import pickle
import io

# Hanya tipe-tipe objek yang aman yang diperbolehkan
class SafeUnpickler(pickle.Unpickler):
    def find_class(self, module, name):
        # Batasi hanya tipe tertentu yang diizinkan
        if module == "__main__" and name in ["SafeClass"]:
            return super().find_class(module, name)
        raise pickle.UnpicklingError(f"Global '{module}.{name}' is not allowed")

class SafeClass:
    def __init__(self, value):
        self.value = value
        

class MaliciousClass:
    def __reduce__(self):
        return (print, ("This is a malicious command!",))

def safe_loads(data):
    return SafeUnpickler(io.BytesIO(data)).load()

# Objek aman
safe_object = SafeClass("This is safe")
serialized_data_safe = pickle.dumps(safe_object)

# Deserialisasi aman
print("Deserializing safe object...")
try:
    deserialized_object = safe_loads(serialized_data_safe)
    print(f"Deserialized object value: {deserialized_object.value}")
except pickle.UnpicklingError as e:
    print(f"Deserialization error: {e}")

# Objek berbahaya
malicious_object = MaliciousClass()
serialized_data_malicious = pickle.dumps(malicious_object)

# Deserialisasi objek berbahaya (harus gagal)
print("Deserializing malicious object...")
try:
    safe_loads(serialized_data_malicious)  # Ini akan menyebabkan error
except pickle.UnpicklingError as e:
    print(f"Deserialization error: {e}")
