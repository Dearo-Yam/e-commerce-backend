package com.example.demo.service;

import com.example.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(int orderId);

    boolean shipOrderById(int orderId);
}
