package com.example.demo.Service.IMPL;

import com.example.demo.Dto.BankAccountDTO;
import com.example.demo.Dto.DepositDTO;
import com.example.demo.Dto.WalletDTO;
import com.example.demo.Dto.WithdrawalDTO;
import com.example.demo.Entity.BankAccount;
import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.Wallet;
import com.example.demo.Repository.BankAccountRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WalletRepository;
import com.example.demo.Service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@Service
public class WalletIMPL implements WalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public static final Logger logger = LoggerFactory.getLogger(WalletIMPL.class);

    // Constructor injection
    public WalletIMPL(WalletRepository walletRepository, BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WalletDTO createWallet(BankAccountDTO bankAccountDTO, Long userId) {
        // Check if bank account exists
        Optional<BankAccount> existingBankAccount = bankAccountRepository.findByCardNumber(bankAccountDTO.getCardNumber());
        if (existingBankAccount.isPresent()) {
            // Create a new wallet
            Wallet wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setCardNumber(bankAccountDTO.getCardNumber());
            wallet.setBalance(existingBankAccount.get().getBalance());

            // Save the wallet
            Wallet savedWallet = walletRepository.save(wallet);

            // Prepare and return the DTO
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setWalletId(savedWallet.getWalletID());
            walletDTO.setCardNumber(savedWallet.getCardNumber());
            walletDTO.setFullName(bankAccountDTO.getFullName());
            walletDTO.setExpirationDate(bankAccountDTO.getExpirationDate());
            walletDTO.setCvv(bankAccountDTO.getCvv());
            walletDTO.setSecurityPin(bankAccountDTO.getSecurityPin());
            return walletDTO;
        } else {
            // Bank account does not exist
            return null;
        }
    }


    public String deposit(DepositDTO depositDTO) {
        Optional<Wallet> senderWalletOptional = walletRepository.findByUserId(depositDTO.getUserId());
        if (senderWalletOptional.isPresent()) {
            Wallet senderWallet = senderWalletOptional.get();
            BigDecimal currentBalance = senderWallet.getBalance();
            BigDecimal depositAmount = depositDTO.getAmount();

            if (currentBalance.compareTo(depositAmount) >= 0) {
                Optional<Wallet> receiverWalletOptional = walletRepository.findById(depositDTO.getReceiverWalletId());
                if (receiverWalletOptional.isPresent()) {
                    Wallet receiverWallet = receiverWalletOptional.get();

                    // Subtract the amount from the sender's wallet
                    senderWallet.setBalance(currentBalance.subtract(depositAmount));
                    walletRepository.save(senderWallet);

                    // Log the transaction for sender
                    transactionRepository.save(new Transaction(
                            depositDTO.getUserId(),
                            "Sent Money",
                            depositAmount.negate(),
                            depositDTO.getReceiverWalletId()
                    ));

                    // Add the amount to the receiver's wallet
                    receiverWallet.setBalance(receiverWallet.getBalance().add(depositAmount));
                    walletRepository.save(receiverWallet);

                    // Log the transaction for receiver
                    transactionRepository.save(new Transaction(
                            receiverWallet.getUserId(),
                            "Received Money",
                            depositAmount,
                            senderWallet.getWalletID()
                    ));

                    return "You paid a deposit of the amount: " + depositAmount + " to the wallet id: " + depositDTO.getReceiverWalletId();
                } else {
                    return "Failure: Receiver wallet not found.";
                }
            } else {
                return "Failure: Balance isn't enough.";
            }
        } else {
            return "Failure: Sender wallet not found.";
        }
    }

    public List<Transaction> getTransactionHistory(Long userId) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    @Override
    public boolean deductFunds(Long userId, BigDecimal amount) {
        // Log the parameters
        logger.info("Attempting to deduct {} from userId {}", amount, userId);

        Optional<Wallet> walletOptional = walletRepository.findByUserId(userId);
        if (walletOptional.isPresent()) {
            Wallet wallet = walletOptional.get();
            BigDecimal currentBalance = wallet.getBalance();
            logger.info("Current balance: {}", currentBalance);

            // Check if the wallet has sufficient balance
            if (currentBalance.compareTo(amount) >= 0) {
                // Deduct the amount from the wallet
                wallet.setBalance(currentBalance.subtract(amount));
                walletRepository.save(wallet);

                // Log the transaction
                transactionRepository.save(new Transaction(
                        userId,
                        "Payment Deduction",
                        amount.negate(),
                        wallet.getWalletID()
                ));

                logger.info("Successfully deducted {} from userId {}", amount, userId);
                return true;
            } else {
                // Insufficient funds
                logger.warn("Insufficient funds for userId {}. Attempted to deduct {}, but balance is {}", userId, amount, currentBalance);
                return false;
            }
        } else {
            // Wallet not found
            logger.warn("Wallet not found for userId {}", userId);
            return false;
        }
    }


    @Override
    public String withdrawal(WithdrawalDTO withdrawalDTO) {
        Optional<Wallet> walletOptional = walletRepository.findByUserId(withdrawalDTO.getUserId());
        if (walletOptional.isPresent()) {
            Wallet wallet = walletOptional.get();
            BigDecimal currentBalance = wallet.getBalance();
            BigDecimal withdrawalAmount = withdrawalDTO.getAmount();

            if (currentBalance.compareTo(withdrawalAmount) >= 0) {
                wallet.setBalance(currentBalance.subtract(withdrawalAmount));
                walletRepository.save(wallet);

                // Log the withdrawal transaction
                transactionRepository.save(new Transaction(
                        withdrawalDTO.getUserId(),
                        "Withdrawal",
                        withdrawalAmount.negate(), // Negative amount for withdrawal
                        wallet.getWalletID()
                ));

                return "You Withdrew an amount of: " + withdrawalAmount;
            } else {
                return "Failure: Balance isn't enough.";
            }
        } else {
            return "Failure: Wallet not found for user.";
        }
    }

}
