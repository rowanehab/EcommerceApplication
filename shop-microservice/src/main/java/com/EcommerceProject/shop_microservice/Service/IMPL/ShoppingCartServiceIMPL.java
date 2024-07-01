package com.EcommerceProject.shop_microservice.Service.IMPL;

import com.EcommerceProject.shop_microservice.Entity.Product;
import com.EcommerceProject.shop_microservice.Entity.ShoppingCart;
import com.EcommerceProject.shop_microservice.Repository.ProductRepository;
import com.EcommerceProject.shop_microservice.Repository.ShoppingCartRepository;
import com.EcommerceProject.shop_microservice.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceIMPL implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ShoppingCart addProductToCart(Long userId, Long productId) {
        Optional<ShoppingCart> cartOpt = shoppingCartRepository.findByUserId(userId);
        ShoppingCart cart = cartOpt.orElseGet(() -> {
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUserId(userId);
            return newCart;
        });

        Optional<Product> productOpt = productRepository.findById(productId);
        productOpt.ifPresent(product -> cart.getProducts().add(product));

        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart removeProductFromCart(Long userId, Long productId) {
        Optional<ShoppingCart> cartOpt = shoppingCartRepository.findByUserId(userId);
        if (cartOpt.isPresent()) {
            ShoppingCart cart = cartOpt.get();
            cart.getProducts().removeIf(product -> product.getId().equals(productId));
            return shoppingCartRepository.save(cart);
        }
        return null;
    }

    @Override
    public ShoppingCart getCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId).orElse(null);
    }
}
