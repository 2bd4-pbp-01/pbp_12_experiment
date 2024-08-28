import { type Account, accounts } from '../main'

export function findAccountByNumber(accountNumber: string): Account | null {
  const account = accounts.find(acc => acc.accountNumber === accountNumber);
  return account && account.isActive ? account : null;
}

export function transferFunds(senderAccountNumber: string, receiverAccountNumber: string, amount: number): string {
  const senderAccount = findAccountByNumber(senderAccountNumber);
  const receiverAccount = findAccountByNumber(receiverAccountNumber);

  // Pengecekan null sebelum mengakses properti atau memodifikasi nilai
  if (!senderAccount) {
    return "Sender account not found or inactive.";
  }

  if (!receiverAccount) {
    return "Receiver account not found or inactive.";
  }

  if (senderAccount.balance < amount) {
    return "Insufficient funds.";
  }

  // Lakukan transfer dengan aman
  senderAccount.balance -= amount;
  receiverAccount.balance += amount;

  return `Transfer of $${amount} from ${senderAccount.accountHolder} to ${receiverAccount.accountHolder} was successful.`;
}
