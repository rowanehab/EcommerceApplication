package com.EcommerceApplication.inventory_microservice.Service.IMPL;


import com.EcommerceApplication.inventory_microservice.Entity.Product;
import com.EcommerceApplication.inventory_microservice.Repository.ProductRepository;
import com.EcommerceApplication.inventory_microservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductIMPL implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
            return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
    @Transactional
    public void updateProductQuantity(Long id, int quantityChange) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        int currentQuantity = product.getQuantity();
        int newQuantity = currentQuantity + quantityChange;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Not enough stock available for product: " + product.getName());
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

}