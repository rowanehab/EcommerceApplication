package com.EcommerceProject.shop_microservice.Repository;

import com.EcommerceProject.shop_microservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}