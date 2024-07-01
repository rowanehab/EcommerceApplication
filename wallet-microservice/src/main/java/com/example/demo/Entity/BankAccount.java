package com.example.demo.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bankaccount")
public class BankAccount {

    @Id
    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    @Column(name = "expiration_date", length = 5, nullable = false)
    private String expirationDate;

    @Column(name = "cvv", length = 3, nullable = false)
    private String cvv;

    @Column(name = "security_pin", length = 4, nullable = false)
    private String securityPin;

    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    public BankAccount() {
    }

    public BankAccount(String cardNumber, String fullName, String expirationDate, String cvv, String securityPin, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.securityPin = securityPin;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "cardNumber='" + cardNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", securityPin='" + securityPin + '\'' +
                ", balance=" + balance +
                '}';
    }
}
