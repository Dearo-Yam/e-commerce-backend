package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderItems;
import com.example.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getPendingOrders();
    ResponseEntity<Order>update(int id, String status);
    List<Product> getItems(int orderId);

    List<Order> getAllOrders();
    Optional<Order> getOrderById(int orderId);

    boolean shipOrderById(int orderId);

}
