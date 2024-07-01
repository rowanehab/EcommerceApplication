package com.example.demo.Repository;

import com.example.demo.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    Optional<BankAccount> findByCardNumber(String cardNumber);
}
