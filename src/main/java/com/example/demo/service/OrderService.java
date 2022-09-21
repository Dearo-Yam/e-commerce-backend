package com.example.demo.service;

import com.example.demo.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Orders> getAllOrders();
    Optional<Orders> getOrderById(int orderId);

    boolean shipOrderById(int orderId);

    int getTotalOrdersShipped();
}
