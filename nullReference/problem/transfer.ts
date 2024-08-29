import { type Account, accounts } from '../main'

export function findAccountByNumber(accountNumber: string): Account | null {
  const account = accounts.find(acc => acc.accountNumber === accountNumber);
  return account && account.isActive ? account : null;
}

export function transferFunds(senderAccountNumber: string, receiverAccountNumber: string, amount: number): string {
  const senderAccount = findAccountByNumber(senderAccountNumber);
  const receiverAccount = findAccountByNumber(receiverAccountNumber);

  // WARN: Tidak ada pengecekan null
  // Berpotensi menyebabkan null reference jika senderAccount null
  // TODO: Lakukan pengecekan untuk null terlebih dahulu
  if (senderAccount.balance < amount) {
    return "Insufficient funds.";
  }

  // WARN: Lakukan transfer tanpa pengecekan null
  // TODO: Lakukan pengecekan untuk null terlebih dahulu
  senderAccount.balance -= amount;
  // Berpotensi menyebabkan null reference jika receiverAccount null
  receiverAccount.balance += amount;

  return `Transfer of $${amount} from ${senderAccount.accountHolder} to ${receiverAccount.accountHolder} was successful.`;
}
