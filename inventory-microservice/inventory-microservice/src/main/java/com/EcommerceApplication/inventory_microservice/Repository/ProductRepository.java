package com.EcommerceApplication.inventory_microservice.Repository;

import com.EcommerceApplication.inventory_microservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}