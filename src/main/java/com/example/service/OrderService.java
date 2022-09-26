package com.example.service;

import com.example.model.Orders;
import com.example.model.Products;
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
    ResponseEntity<Orders>update(int id, String status);
    List<Products> getItems(int orderId);
    
    double getAvgTimeToShip();
    
    List<Orders> getTopSellingItems();
//    ResponseEntity<String> update(int id, String status);

//    List<Orders> getItems(int orderId);
}
