package com.EcommerceProject.shop_microservice.Controller;

import com.EcommerceProject.shop_microservice.Dto.ProductDto;
import com.EcommerceProject.shop_microservice.Entity.Product;
import com.EcommerceProject.shop_microservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        // Call the service method to create the product
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Product createdProduct = productService.createProduct(product);

        if (createdProduct != null) {
            // Product created successfully
            return ResponseEntity.ok(createdProduct);
        } else {
            // Handle the case where product creation failed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        // Call the service method to update the product
        Product product = new Product();
        product.setId(id);  // Set the ID from the path variable
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Product updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct != null) {
            // Product updated successfully
            return ResponseEntity.ok(updatedProduct);
        } else {
            // Handle the case where product update failed
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");

    }
}