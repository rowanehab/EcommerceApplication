package com.example.demo.Dto;

public class WalletDTO {
    private Long walletId; // Adding walletId field
    private String cardNumber;
    private String fullName;
    private String expirationDate;
    private String cvv;
    private String securityPin;

    // Constructors
    public WalletDTO() {
    }

    public WalletDTO(Long walletId, String cardNumber, String fullName, String expirationDate, String cvv, String securityPin) {
        this.walletId = walletId;
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.securityPin = securityPin;
    }

    // Getters and Setters
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
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

    // toString method
    @Override
    public String toString() {
        return "WalletDTO{" +
                "walletId=" + walletId +
                ", cardNumber='" + cardNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", securityPin='" + securityPin + '\'' +
                '}';
    }
}
