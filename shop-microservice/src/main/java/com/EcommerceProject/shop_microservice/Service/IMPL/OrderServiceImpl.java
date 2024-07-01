package com.EcommerceProject.shop_microservice.Service.IMPL;
import com.EcommerceProject.shop_microservice.Client.WalletClient;
import com.EcommerceProject.shop_microservice.Entity.Order;
import com.EcommerceProject.shop_microservice.Entity.Product;
import com.EcommerceProject.shop_microservice.Repository.OrderRepository;
import com.EcommerceProject.shop_microservice.Repository.ProductRepository;
import com.EcommerceProject.shop_microservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WalletClient walletClient;
    @Override
    public Order createOrder(Long userId, List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        // Calculate total amount
        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Deduct amount from wallet
        ResponseEntity<String> response = walletClient.deductFunds(userId, totalAmount);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Payment failed, insufficient funds");
        }

        // Create and save the order
        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(products);
        order.setStatus("CREATED");

        return orderRepository.save(order);
    }




    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null; // Or throw an exception if the order is not found
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
