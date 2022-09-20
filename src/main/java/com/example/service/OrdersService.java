package com.example.service;

import com.example.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrdersService {

    List<Order> getAll();
    ResponseEntity<String> update(Order r, int id);
    Optional<Order> getSaleById(int id);
    ResponseEntity<String>updateById(int id, String email);
}
