// import { transferFunds } from "./problem/transfer";
import { transferFunds } from "./solution/transfer";

export type Account = {
  accountNumber: string;
  accountHolder: string;
  balance: number;
  isActive: boolean; // Menunjukkan apakah rekening aktif atau tidak
};

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
  {
    accountNumber: "123123123",
    accountHolder: "bayu",
    balance: 2000,
    isActive: true,
  },
];

// Kasus 0: Transfer berhasil ke rekening aktif
console.log(transferFunds("1234567890", "123123123", 500));

// Kasus 1: Transfer gagal ke rekening tidak aktif
console.log(transferFunds("1234567890", "0987654321", 500)); // Harus gagal karena rekening penerima tidak aktif

// Kasus 2: Transfer gagal karena rekening penerima tidak aktif
console.log(transferFunds("1234567890", "111111111", 500)); // Harus gagal karena rekening penerima tidak ada

// Kasus 3: Transfer gagal karena dana tidak cukup
console.log(transferFunds("1234567890", "123123123", 2000)); // Harus gagal karena dana pengirim tidak cukup

// Kasus 4: Transfer gagal karena rekening pengirim tidak aktif
console.log(transferFunds("0987654321", "1234567890", 100)); // Harus gagal karena rekening pengirim tidak aktif
