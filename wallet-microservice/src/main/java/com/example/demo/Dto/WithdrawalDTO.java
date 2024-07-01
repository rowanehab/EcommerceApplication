package com.example.demo.Dto;

import java.math.BigDecimal;

public class WithdrawalDTO {

    private Long userId;
    private BigDecimal amount;

    public WithdrawalDTO() {
    }

    public WithdrawalDTO(Long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "DepositDTO{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
