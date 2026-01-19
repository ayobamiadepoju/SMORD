package com.smord.product_service.controller;

import com.smord.product_service.exception.ProductNotFoundException;
import com.smord.product_service.model.Product;
import com.smord.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/store")
    public ResponseEntity<List<Product>> getStoreProducts(
            @RequestParam String storeName){
        return ResponseEntity.ok(productService.getStoreProducts(storeName));
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getOneProduct(
            @PathVariable String productCode) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getOneProduct(productCode));
    }

    @GetMapping("/search")
    public ResponseEntity<Product> getProduct(
            @RequestParam String storeName,
            @RequestParam String productCode
    ) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(storeName, productCode));
    }
}
