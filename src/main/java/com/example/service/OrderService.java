package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderItems;
import com.example.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> getPendingOrders();
    ResponseEntity<Order>update(int id, String status);
    List<Product> getItems(int orderId);
}
