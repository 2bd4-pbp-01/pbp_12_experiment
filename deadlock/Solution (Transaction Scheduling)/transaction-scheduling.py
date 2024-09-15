import threading
import time

# Locks representing resources
lock_a = threading.Lock()
lock_b = threading.Lock()

# Result dictionary to track deadlocks
results = {
    "FCFS": {"deadlock": 0, "success": 0},
    "Priority": {"deadlock": 0, "success": 0},
}

# Transaction 1 function
def transaction_1(scheduler):
    try:
        print(f"{scheduler}: T1 attempting to lock A")
        lock_a.acquire(timeout=1)
        print(f"{scheduler}: T1 locked A")
        time.sleep(0.5)  # Simulate some processing time
        
        print(f"{scheduler}: T1 attempting to lock B")
        lock_b.acquire(timeout=1)
        print(f"{scheduler}: T1 locked B")
        time.sleep(0.5)  # Simulate some processing time

    except Exception as e:
        print(f"{scheduler}: T1 deadlock detected")
        results[scheduler]["deadlock"] += 1
    finally:
        # Release locks if held
        if lock_a.locked():
            lock_a.release()
        if lock_b.locked():
            lock_b.release()
        print(f"{scheduler}: T1 finished")
        results[scheduler]["success"] += 1

# Transaction 2 function
def transaction_2(scheduler):
    try:
        print(f"{scheduler}: T2 attempting to lock B")
        lock_b.acquire(timeout=1)
        print(f"{scheduler}: T2 locked B")
        time.sleep(0.5)  # Simulate some processing time
        
        print(f"{scheduler}: T2 attempting to lock A")
        lock_a.acquire(timeout=1)
        print(f"{scheduler}: T2 locked A")
        time.sleep(0.5)  # Simulate some processing time

    except Exception as e:
        print(f"{scheduler}: T2 deadlock detected")
        results[scheduler]["deadlock"] += 1
    finally:
        # Release locks if held
        if lock_b.locked():
            lock_b.release()
        if lock_a.locked():
            lock_a.release()
        print(f"{scheduler}: T2 finished")
        results[scheduler]["success"] += 1

# FCFS Scheduling
def fcfs_scheduling():
    print("\nStarting FCFS Scheduling")
    t1 = threading.Thread(target=transaction_1, args=("FCFS",))
    t2 = threading.Thread(target=transaction_2, args=("FCFS",))

    # Start in FCFS order
    t1.start()
    t2.start()

    t1.join()
    t2.join()

# Priority Scheduling
def priority_scheduling():
    print("\nStarting Priority Scheduling")
    # Assume T2 has higher priority and starts first
    t2 = threading.Thread(target=transaction_2, args=("Priority",))
    t1 = threading.Thread(target=transaction_1, args=("Priority",))

    # Start T2 first due to higher priority
    t2.start()
    t1.start()

    t2.join()
    t1.join()

# Run both scheduling experiments
fcfs_scheduling()
priority_scheduling()

# Print results
print("\nResults:")
print(results)
