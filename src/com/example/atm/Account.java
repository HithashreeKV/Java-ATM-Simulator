package com.example.atm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String accountId;
    private final String ownerName;
    private double balance;
    private final List<Transaction> transactions;

    public Account(String accountId, String ownerName, double initialBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction(Transaction.Type.CREATE_ACCOUNT, initialBalance, balance));
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        transactions.add(new Transaction(Transaction.Type.DEPOSIT, amount, balance));
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance.");
        balance -= amount;
        transactions.add(new Transaction(Transaction.Type.WITHDRAW, amount, balance));
    }

    public double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
