import { type Account, accounts } from '../main'

export function findAccountByNumber(accountNumber: string): Account | null {
  const account = accounts.find(acc => acc.accountNumber === accountNumber);
  return account && account.isActive ? account : null;
}

export function transferFunds(senderAccountNumber: string, receiverAccountNumber: string, amount: number): string {
  const senderAccount = findAccountByNumber(senderAccountNumber);
  const receiverAccount = findAccountByNumber(receiverAccountNumber);

  // Tidak ada pengecekan null
  if (senderAccount.balance < amount) { // Berpotensi menyebabkan null reference jika senderAccount null
    return "Insufficient funds.";
  }

  // Lakukan transfer tanpa pengecekan null
  senderAccount.balance -= amount;
  receiverAccount.balance += amount; // Berpotensi menyebabkan null reference jika receiverAccount null

  return `Transfer of $${amount} from ${senderAccount.accountHolder} to ${receiverAccount.accountHolder} was successful.`;
}
