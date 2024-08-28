package Latency;

public class TransactionProcessing{

    public static boolean validateCreditCard(String cardNumber) {
        try {
            // Simulasi validasi kartu kredit (misalnya, panggilan API)
            System.out.println("Validating credit card...");
            Thread.sleep(2000);
            return true; // Asumsikan validasi sukses
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkBalance(String accountId) {
        try {
            // Simulasi pengecekan saldo (misalnya, akses database)
            System.out.println("Checking balance...");
            Thread.sleep(1500);
            return true; // Asumsikan saldo mencukupi
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean completeTransaction(String transactionId) {
        try {
            // Simulasi penyelesaian transaksi (misalnya, simpan ke database)
            System.out.println("Completing transaction...");
            Thread.sleep(1000);
            return true; // Asumsikan transaksi berhasil
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void processTransaction(String cardNumber, String accountId, String transactionId) {
        System.out.println("Starting transaction processing...");

        boolean isCardValid = validateCreditCard(cardNumber);
        if (!isCardValid) {
            System.out.println("Transaction failed: Invalid credit card.");
            return;
        }

        boolean isBalanceSufficient = checkBalance(accountId);
        if (!isBalanceSufficient) {
            System.out.println("Transaction failed: Insufficient balance.");
            return;
        }

        boolean isTransactionComplete = completeTransaction(transactionId);
        if (isTransactionComplete) {
            System.out.println("Transaction completed successfully.");
        } else {
            System.out.println("Transaction failed during completion.");
        }

        System.out.println("Transaction processing finished.");
    }

    public static void main(String[] args) {
        String cardNumber = "1234-5678-9012-3456";
        String accountId = "account123";
        String transactionId = "trans001";

        processTransaction(cardNumber, accountId, transactionId);
    }
}

