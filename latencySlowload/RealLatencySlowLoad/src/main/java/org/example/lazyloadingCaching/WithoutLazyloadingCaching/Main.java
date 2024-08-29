package org.example.lazyloadingCaching.WithoutLazyloadingCaching;



public class Main {
    public static void main(String[] args) {
        TransactionDao dao = new TransactionDao();
        int transactionId = 1;

        long startTime = System.currentTimeMillis();
        System.out.println("Loading transaction for ID: " + transactionId);
        Transaction transaction1 = dao.getTransaction(transactionId);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (transaction1 != null) {
            System.out.println("First retrieval: " + transaction1);
        } else {
            System.out.println("Transaction not found.");
        }
        System.out.println("Time taken for first retrieval: " + duration + " ms");

        startTime = System.currentTimeMillis();
        System.out.println("Loading transaction for ID: " + transactionId);
        Transaction transaction2 = dao.getTransaction(transactionId);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;

        if (transaction2 != null) {
            System.out.println("Second retrieval: " + transaction2);
        } else {
            System.out.println("Transaction not found.");
        }
        System.out.println("Time taken for second retrieval: " + duration + " ms");
    }
    
}

