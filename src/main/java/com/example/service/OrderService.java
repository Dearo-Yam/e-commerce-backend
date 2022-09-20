package com.example.service;

import com.example.entity.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Orders> getPendingOrders();
    ResponseEntity<String>update(int id, String status);
}
