package com.EcommerceProject.shop_microservice.Client;


import com.EcommerceProject.shop_microservice.Config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "wallet-microservice", url = "http://localhost:8888")
public interface WalletClient {

    @PutMapping("/api/wallets/deduct")
    ResponseEntity<String> deductFunds(@RequestParam("userId") Long userId, @RequestParam("amount") BigDecimal amount);
}


