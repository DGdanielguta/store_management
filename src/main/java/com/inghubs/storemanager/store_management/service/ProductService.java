package com.inghubs.storemanager.store_management.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inghubs.storemanager.store_management.data.entity.Product;
import com.inghubs.storemanager.store_management.data.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    
    @Transactional
    public Product createProduct(String name, BigDecimal price, Integer quantity) {
        log.info("Create product was called: name={}, price={}, qty={}", name, price, quantity);
        Product product = new Product(name, price, quantity);
        productRepository.save(product);
        log.info("Product saved: {}", product);
        return product;
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        log.info("Get product by id was called: id={}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product not found with id={}", id);
                    return new RuntimeException("Product not found");
                });
    }

    @Transactional(readOnly = true)
    public Product getProductByName(String name) {
        log.info("Get product by name was called: name={}", name);
        return productRepository.findByName(name)
                .orElseThrow(() -> {
                    log.warn("Product not found with name={}", name);
                    return new RuntimeException("Product not found");
                });
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.info("Get all products was called");
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Delete product was called: id={}", id);
        productRepository.deleteById(id);
        log.info("Product deleted with id={}", id);
    }

}
