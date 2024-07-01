package com.example.demo.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Transaction() {}

    public Transaction(Long userId, String transactionType, BigDecimal amount, Long walletId) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.walletId = walletId;
        this.timestamp = LocalDateTime.now();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", walletId=" + walletId +
                ", timestamp=" + timestamp +
                '}';
    }
}
