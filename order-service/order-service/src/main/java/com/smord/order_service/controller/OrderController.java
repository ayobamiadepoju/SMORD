package com.smord.order_service.controller;

import com.smord.order_service.dto.CreateOrderRequest;
import com.smord.order_service.dto.OrderResponse;
import com.smord.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody CreateOrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}
