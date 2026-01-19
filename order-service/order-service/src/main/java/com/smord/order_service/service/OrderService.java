package com.smord.order_service.service;

import com.smord.order_service.dto.CreateOrderRequest;
import com.smord.order_service.dto.OrderResponse;
import com.smord.order_service.model.Order;
import com.smord.order_service.model.OrderStatus;
import com.smord.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest request){

        Order order = new Order();
        order.setStoreName(request.getStoreName());
        order.setQuantity(request.getQuantity());
        order.setProductCode(request.getProductCode());
        order.setStatus(OrderStatus.CREATED);

        Order savedOrder = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setStatus(savedOrder.getStatus());
        response.setMessage("Order created successfully");

        return response;
    }
}
