package com.example.demo.Controller;


import com.example.demo.Dto.BankAccountDTO;
import com.example.demo.Dto.DepositDTO;
import com.example.demo.Dto.WalletDTO;
import com.example.demo.Dto.WithdrawalDTO;
import com.example.demo.Entity.Transaction;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final
    WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Constructor injection
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/CreateWallet")
    public ResponseEntity<?> createWallet(@RequestBody BankAccountDTO bankAccountDTO) {
        // For testing purposes, directly set the userId
        Long userId = userService.getCurrentUserId();

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Call the service method to create the wallet
        WalletDTO walletDTO = walletService.createWallet(bankAccountDTO, userId);

        if (walletDTO != null) {
            // Wallet created successfully
            return ResponseEntity.ok(walletDTO);
        } else {
            // Bank account does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found for card number: " + bankAccountDTO.getCardNumber());
        }
    }

    @PutMapping("/deduct")
    public ResponseEntity<String> deductFunds(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        boolean success = walletService.deductFunds(userId, amount);
        if (success) {
            return ResponseEntity.ok("Funds deducted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds or wallet not found.");
        }
    }
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO) {
        // Get the current authenticated user's userId
        Long userId = userService.getCurrentUserId();

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Set the userId in the depositDTO
        depositDTO.setUserId(userId);

        // Call the service method to handle the deposit
        String result = walletService.deposit(depositDTO);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody WithdrawalDTO withdrawalDTO) {
        // Get the current authenticated user's userId
        Long userId = userService.getCurrentUserId();

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Set the userId in the withdrawalDTO
        withdrawalDTO.setUserId(userId);

        // Call the service method to handle the withdrawal
        String result = walletService.withdrawal(withdrawalDTO);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactionHistory() {
        Long userId = userService.getCurrentUserId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        List<Transaction> transactions = walletService.getTransactionHistory(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/Happy")
    public String Happy() {
     return "Happy";
    }


}
