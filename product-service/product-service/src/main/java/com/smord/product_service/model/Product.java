package com.smord.product_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    private BigDecimal price;
    private Integer quantity;

    private String sellerEmail;
}
