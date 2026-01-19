package com.smord.product_service.repository;

import com.smord.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreName(String storeName);

    Optional<Product> findByStoreNameAndProductCode(String storeName, String productCode);

    Optional<Product> findByProductCode(String productCode);
}
