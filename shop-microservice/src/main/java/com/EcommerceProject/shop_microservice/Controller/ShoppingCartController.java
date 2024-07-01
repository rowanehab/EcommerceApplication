package com.EcommerceProject.shop_microservice.Controller;

import com.EcommerceProject.shop_microservice.Entity.ShoppingCart;
import com.EcommerceProject.shop_microservice.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ShoppingCart addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        return shoppingCartService.addProductToCart(userId, productId);
    }

    @DeleteMapping("/remove")
    public ShoppingCart removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        return shoppingCartService.removeProductFromCart(userId, productId);
    }

    @GetMapping
    public ShoppingCart getCartByUserId(@RequestParam Long userId) {
        return shoppingCartService.getCartByUserId(userId);
    }}