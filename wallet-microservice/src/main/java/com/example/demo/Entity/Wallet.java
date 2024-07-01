package com.example.demo.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wallet_id")
    private Long walletID;

    @Column(name = "user_id", length=25,nullable = false)
    private Long userId;

    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    public Wallet() {
    }

    public Wallet(Long walletID, Long userId, String cardNumber, BigDecimal balance) {
        this.walletID = walletID;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public Long getWalletID() {
        return walletID;
    }

    public void setWalletID(Long walletID) {
        this.walletID = walletID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletID=" + walletID +
                ", userId=" + userId +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}