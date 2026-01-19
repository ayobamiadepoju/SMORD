package com.smord.order_service.dto;

import com.smord.order_service.model.OrderStatus;
import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;
    private String message;
    private OrderStatus status;
}
