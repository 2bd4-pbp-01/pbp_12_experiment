import threading
import time

class ResourceA:
    def method_a(self, resource_b):
        with lock_a:
            print(f"{threading.current_thread().name}: Locked ResourceA")
            time.sleep(0.05)  # Simulate some work
            print(f"{threading.current_thread().name}: Trying to lock ResourceB")
            with lock_b:
                print(f"{threading.current_thread().name}: Locked ResourceB")

class ResourceB:
    def method_b(self, resource_a):
        with lock_a:
            print(f"{threading.current_thread().name}: Locked ResourceA")
            time.sleep(0.05)  # Simulate some work
            print(f"{threading.current_thread().name}: Trying to lock ResourceB")
            with lock_b:
                print(f"{threading.current_thread().name}: Locked ResourceB")

lock_a = threading.Lock()
lock_b = threading.Lock()

def thread1_func(resource_a, resource_b):
    resource_a.method_a(resource_b)

def thread2_func(resource_a, resource_b):
    resource_b.method_b(resource_a)

if __name__ == "__main__":
    resource_a = ResourceA()
    resource_b = ResourceB()

    thread1 = threading.Thread(target=thread1_func, args=(resource_a, resource_b), name="Thread 1")
    thread2 = threading.Thread(target=thread2_func, args=(resource_a, resource_b), name="Thread 2")

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
