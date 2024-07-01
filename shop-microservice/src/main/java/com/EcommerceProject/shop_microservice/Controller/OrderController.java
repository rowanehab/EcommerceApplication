package com.EcommerceProject.shop_microservice.Controller;


import com.EcommerceProject.shop_microservice.Client.WalletClient;
import com.EcommerceProject.shop_microservice.Dto.OrderRequestDto;
import com.EcommerceProject.shop_microservice.Entity.Order;
import com.EcommerceProject.shop_microservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private WalletClient walletClient;

    /*@PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest) {
        Order order = orderService.createOrder(orderRequest.getUserId(), orderRequest.getProductIds());
        return ResponseEntity.ok(order);
    }*/

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest) {
        Order order = orderService.createOrder(orderRequest.getUserId(), orderRequest.getProductIds());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/deducttest")
    public ResponseEntity<String> testDeductFunds(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        ResponseEntity<String> response = walletClient.deductFunds(userId, amount);
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Deduction successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deduction failed: " + response.getBody());
        }
    }


    @PutMapping("/updateStatus")
    public Order updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @GetMapping
    public List<Order> getOrdersByUserId(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }
}
