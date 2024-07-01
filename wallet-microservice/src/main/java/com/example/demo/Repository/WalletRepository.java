package com.example.demo.Repository;

import com.example.demo.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUserId(Long userId);

}
