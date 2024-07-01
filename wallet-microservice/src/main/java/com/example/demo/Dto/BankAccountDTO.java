package com.example.demo.Dto;


import java.math.BigDecimal;

public class BankAccountDTO {
    private String cardNumber;
    private String fullName;
    private String expirationDate;
    private String cvv;
    private String securityPin;
    private BigDecimal balance;

    // Constructors
    public BankAccountDTO() {
    }

    public BankAccountDTO(String cardNumber, String fullName, String expirationDate, String cvv, String securityPin, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.securityPin = securityPin;
        this.balance = balance;
    }

    // Getters and Setters
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

    // toString method
    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "cardNumber='" + cardNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", securityPin='" + securityPin + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
