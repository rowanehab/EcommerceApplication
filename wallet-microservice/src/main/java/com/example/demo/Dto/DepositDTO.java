package com.example.demo.Dto;

import java.math.BigDecimal;

public class DepositDTO {
    private Long userId;
    private BigDecimal amount;
    private Long receiverWalletId;

    public DepositDTO() {
    }

    public DepositDTO(Long userId, BigDecimal amount, Long receiverWalletId) {
        this.userId = userId;
        this.amount = amount;
        this.receiverWalletId = receiverWalletId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(Long receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    @Override
    public String toString() {
        return "DepositDTO{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", receiverWalletId=" + receiverWalletId +
                '}';
    }
}
