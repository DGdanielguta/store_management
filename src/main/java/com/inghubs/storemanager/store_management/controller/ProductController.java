package com.inghubs.storemanager.store_management.controller;

import com.inghubs.storemanager.store_management.data.entity.Product;
import com.inghubs.storemanager.store_management.service.ProductService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public record CreateProductRequest(
        @NotBlank String name, @NotNull @Positive @DecimalMin("0.0") java.math.BigDecimal price, @NotNull @Min(0) Integer quantity) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(@Valid @RequestBody CreateProductRequest request) {
        log.info("Create product request received: {}", request);
        return productService.createProduct(request.name(), request.price(), request.quantity());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Product getProductById(@Min(1) Long id) {
        log.info("Get product by id request received: id={}", id);
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Product getProductByName(@NotBlank String name) {
        log.info("Get product by name request received: name={}", name);
        return productService.getProductByName(name);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public java.util.List<Product> getAllProducts() {
        log.info("Get all products request received");
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@Min(1) Long id) {
        log.info("Delete product request received: id={}", id);
        productService.deleteProduct(id);
    }
}