package com.EcommerceApplication.inventory_microservice.Service;

import com.EcommerceApplication.inventory_microservice.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    public Product updateProduct(Long id, Product productDetails);

    void deleteProduct(Long id);
    void updateProductQuantity(Long id, int quantityChange);
}
