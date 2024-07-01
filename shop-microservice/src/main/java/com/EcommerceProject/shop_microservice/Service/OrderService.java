package com.EcommerceProject.shop_microservice.Service;

import com.EcommerceProject.shop_microservice.Entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, List<Long> productIds);
    Order updateOrderStatus(Long orderId, String status);
    List<Order> getOrdersByUserId(Long userId);
    Order getOrderById(Long orderId);
}
