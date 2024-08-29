import { expect, test } from "bun:test";
import { accounts } from "./main";
// import { findAccountByNumber, transferFunds } from "./problem/transfer";
import { findAccountByNumber, transferFunds } from "./solution/transfer";

test("Cari akun aktif", () => {
  expect(findAccountByNumber("1234567890")).toBe(accounts[0]);
});

test("Cari akun non-aktif", () => {
  expect(findAccountByNumber("000000000")).toBeNull();
});

test("Transfer berhasil ke rekening aktif", () => {
  expect(transferFunds("1234567890", "123123123", 500)).toContain("500");
});

test("Transfer gagal karena rekening non-aktif", () => {
  expect(transferFunds("1234567890", "0987654321", 500)).toContain("inactive");
});

test("Transfer gagal karena rekening tidak ada", () => {
  expect(transferFunds("1234567890", "696969666", 500)).toContain("not found");
});

test("Transfer gagal karena saldo kurang", () => {
  expect(transferFunds("1234567890", "123123123", 5000)).toContain(
    "Insufficient",
  );
});
