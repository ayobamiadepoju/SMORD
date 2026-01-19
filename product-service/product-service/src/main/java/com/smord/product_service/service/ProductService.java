package com.smord.product_service.service;

import com.smord.product_service.exception.ProductNotFoundException;
import com.smord.product_service.model.Product;
import com.smord.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        log.info("product created....{}", product);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        log.info("searching for all products");
        return productRepository.findAll();
    }

    public Product getOneProduct(String productCode) throws ProductNotFoundException {
        return productRepository.findByProductCode(productCode)
                .orElseThrow(() ->
                        new ProductNotFoundException("There is no product with such code!")
                );
    }

    public List<Product> getStoreProducts(String storeName){
        log.info("searching for a seller's products");
        return productRepository.findByStoreName(storeName);
    }

    public Product getProduct(String storeName, String productCode) throws ProductNotFoundException {
        return productRepository
                .findByStoreNameAndProductCode(storeName, productCode)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found for this store")
                );
    }

}
