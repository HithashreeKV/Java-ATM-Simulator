package com.example.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAW, CREATE_ACCOUNT }

    private final Type type;
    private final double amount;
    private final LocalDateTime timestamp;
    private final double balanceAfter;

    public Transaction(Type type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %-8s | Amount: %.2f | Balance after: %.2f",
                timestamp.format(f), type, amount, balanceAfter);
    }

    // getters (if needed)
    public Type getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getBalanceAfter() { return balanceAfter; }
}
