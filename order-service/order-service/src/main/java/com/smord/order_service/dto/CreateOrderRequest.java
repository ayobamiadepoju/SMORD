package com.smord.order_service.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private String storeName;
    private String productCode;
    private Integer quantity;
}
