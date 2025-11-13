package com.inghubs.storemanager.store_management.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inghubs.storemanager.store_management.data.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);    

    Optional<Product> findById(Long id);
}
