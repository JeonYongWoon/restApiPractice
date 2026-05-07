package com.example.restapipractice.product.repository;

import com.example.restapipractice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
