class ResourceA {
    public synchronized void methodA(ResourceB resourceB) {
        System.out.println(Thread.currentThread().getName() + ": Locked ResourceA");
        try {
            Thread.sleep(50); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Trying to lock ResourceB");
        resourceB.methodB();
    }
    
    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + ": Inside ResourceA.methodB");
    }
}

class ResourceB {
    public synchronized void methodA(ResourceA resourceA) {
        System.out.println(Thread.currentThread().getName() + ": Locked ResourceB");
        try {
            Thread.sleep(50); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Trying to lock ResourceA");
        resourceA.methodB();
    }
    
    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + ": Inside ResourceB.methodB");
    }
}

public class DeadlockPrevention {
    public static void main(String[] args) {
        final ResourceA resourceA = new ResourceA();
        final ResourceB resourceB = new ResourceB();
        
        // Thread1 locks ResourceA first, then ResourceB
        Thread thread1 = new Thread(() -> {
            synchronized(resourceA) {
                System.out.println("Thread 1: Locked ResourceA");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized(resourceB) {
                    System.out.println("Thread 1: Locked ResourceB");
                }
            }
        }, "Thread 1");
        
        // Thread2 locks ResourceA first, then ResourceB
        Thread thread2 = new Thread(() -> {
            synchronized(resourceA) {
                System.out.println("Thread 2: Locked ResourceA");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized(resourceB) {
                    System.out.println("Thread 2: Locked ResourceB");
                }
            }
        }, "Thread 2");
        
        thread1.start();
        thread2.start();
    }
}
