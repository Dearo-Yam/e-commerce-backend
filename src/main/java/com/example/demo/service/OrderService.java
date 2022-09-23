package com.example.demo.service;

import com.example.demo.model.Orders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Orders> getAllOrders();
    Optional<Orders> getOrderById(int orderId);

    boolean shipOrderById(int orderId);

    int getTotalOrdersShipped();

    List<Orders> getPendingOrders();
    
    double getAvgTimeToShip();
//    ResponseEntity<String> update(int id, String status);

//    List<Orders> getItems(int orderId);
}
