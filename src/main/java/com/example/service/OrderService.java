package com.example.service;

import com.example.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> getPendingOrders();
    ResponseEntity<String>update(int id, String status);

    List<Order> getItems(int orderId);
}
