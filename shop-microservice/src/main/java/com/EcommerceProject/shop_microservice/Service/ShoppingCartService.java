package com.EcommerceProject.shop_microservice.Service;

import com.EcommerceProject.shop_microservice.Entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addProductToCart(Long userId, Long productId);
    ShoppingCart removeProductFromCart(Long userId, Long productId);
    ShoppingCart getCartByUserId(Long userId);
}
