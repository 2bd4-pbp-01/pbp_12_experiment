export type Account = {
  accountNumber: string;
  accountHolder: string;
  balance: number;
  isActive: boolean; // Menunjukkan apakah rekening aktif atau tidak
}

// Contoh data rekening yang akan digunakan
export const accounts: Account[] = [
  {
    accountNumber: "1234567890",
    accountHolder: "John Doe",
    balance: 1000,
    isActive: true,
  },
  {
    accountNumber: "0987654321",
    accountHolder: "Jane Smith",
    balance: 2000,
    isActive: false, // Rekening ini tidak aktif
  },
]
