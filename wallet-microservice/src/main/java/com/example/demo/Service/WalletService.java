package com.example.demo.Service;

import com.example.demo.Dto.BankAccountDTO;
import com.example.demo.Dto.DepositDTO;
import com.example.demo.Dto.WalletDTO;
import com.example.demo.Dto.WithdrawalDTO;
import com.example.demo.Entity.Transaction;

import java.util.List;

public interface WalletService {
    WalletDTO createWallet(BankAccountDTO bankAccountDTO, Long userId);
    String deposit(DepositDTO depositDTO);
    String withdrawal(WithdrawalDTO withdrawalDTO);
    List<Transaction> getTransactionHistory(Long userId);
}
