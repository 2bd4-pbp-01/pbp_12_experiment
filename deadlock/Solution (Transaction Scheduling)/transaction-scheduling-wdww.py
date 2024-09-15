import threading
import time
import random

# Locks representing resources (e.g., seat availability)
seat_lock = threading.Lock()
payment_lock = threading.Lock()

# Transaction timestamps
timestamp = {
    "T1": 1,  # Transaction T1 has an earlier timestamp (higher priority)
    "T2": 2   # Transaction T2 has a later timestamp (lower priority)
}

# Results to track deadlock and successful executions
results = {
    "Wait-Die": {"deadlock": 0, "success": 0},
    "Wound-Wait": {"deadlock": 0, "success": 0}
}

# Simulate ticket booking (T1)
def booking_transaction(scheduler, txn_name):
    try:
        print(f"{scheduler}: {txn_name} attempting to lock seat")
        if seat_lock.acquire(timeout=1):
            print(f"{scheduler}: {txn_name} locked seat")
            time.sleep(random.uniform(0.5, 1.0))  # Simulate seat allocation time
            
            print(f"{scheduler}: {txn_name} attempting to lock payment")
            if payment_lock.acquire(timeout=1):
                print(f"{scheduler}: {txn_name} locked payment and completed booking")
                time.sleep(random.uniform(0.5, 1.0))  # Simulate payment processing
            else:
                raise Exception(f"{scheduler}: {txn_name} failed to lock payment")
        else:
            raise Exception(f"{scheduler}: {txn_name} failed to lock seat")
    except Exception as e:
        print(e)
        results[scheduler]["deadlock"] += 1
    finally:
        if seat_lock.locked():
            seat_lock.release()
        if payment_lock.locked():
            payment_lock.release()
        print(f"{scheduler}: {txn_name} finished")
        results[scheduler]["success"] += 1

# Simulate ticket search (T2)
def search_transaction(scheduler, txn_name):
    try:
        print(f"{scheduler}: {txn_name} attempting to lock payment")
        if payment_lock.acquire(timeout=1):
            print(f"{scheduler}: {txn_name} locked payment")
            time.sleep(random.uniform(0.5, 1.0))  # Simulate search time
            
            print(f"{scheduler}: {txn_name} attempting to lock seat")
            if seat_lock.acquire(timeout=1):
                print(f"{scheduler}: {txn_name} locked seat and completed search")
                time.sleep(random.uniform(0.5, 1.0))  # Simulate seat check
            else:
                raise Exception(f"{scheduler}: {txn_name} failed to lock seat")
        else:
            raise Exception(f"{scheduler}: {txn_name} failed to lock payment")
    except Exception as e:
        print(e)
        results[scheduler]["deadlock"] += 1
    finally:
        if payment_lock.locked():
            payment_lock.release()
        if seat_lock.locked():
            seat_lock.release()
        print(f"{scheduler}: {txn_name} finished")
        results[scheduler]["success"] += 1

# Wait-Die Scheduling
def wait_die_scheduling():
    print("\nStarting Wait-Die Scheduling")
    
    def wait_die(txn_older, txn_younger):
        # If older transaction wants a lock, it waits. Younger dies (is restarted).
        if timestamp[txn_older] < timestamp[txn_younger]:
            booking_transaction("Wait-Die", txn_older)
            search_transaction("Wait-Die", txn_younger)
        else:
            search_transaction("Wait-Die", txn_younger)
            booking_transaction("Wait-Die", txn_older)

    # Threading for transactions
    t1 = threading.Thread(target=wait_die, args=("T1", "T2"))
    
    t1.start()
    t1.join()

# Wound-Wait Scheduling
def wound_wait_scheduling():
    print("\nStarting Wound-Wait Scheduling")
    
    def wound_wait(txn_older, txn_younger):
        # If older transaction wants a lock, it wounds (forces rollback of) the younger transaction.
        if timestamp[txn_older] < timestamp[txn_younger]:
            booking_transaction("Wound-Wait", txn_older)
            # Force the younger transaction to "die" and restart
            search_transaction("Wound-Wait", txn_younger)
        else:
            search_transaction("Wound-Wait", txn_younger)
            booking_transaction("Wound-Wait", txn_older)

    # Threading for transactions
    t1 = threading.Thread(target=wound_wait, args=("T1", "T2"))
    
    t1.start()
    t1.join()

# Run both scheduling experiments
wait_die_scheduling()
wound_wait_scheduling()

# Print results
print("\nResults:")
print(results)
