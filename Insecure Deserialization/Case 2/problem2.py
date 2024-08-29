import pickle

class MaliciousClass:
    def __reduce__(self):
        return (print, ("This is a malicious command!",))

# Objek berbahaya
malicious_object = MaliciousClass()
serialized_data = pickle.dumps(malicious_object)

# Deserialisasi (rentan)
print("Deserializing malicious object...")
pickle.loads(serialized_data)  # Ini akan menjalankan kode berbahaya
