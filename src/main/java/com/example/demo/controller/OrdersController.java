package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {
    @Autowired
    OrderService service;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Order controller";
    }

    // Getting all Orders:
    @GetMapping("/all")
    @ResponseBody
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    // Getting a single Order by ID:
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int orderId) {
        Optional<Order> foundOrder = service.getOrderById(orderId);
        if(foundOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(foundOrder.get());
        }
        System.out.println("ERROR: Order with ID: " + orderId + " does not exist!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{id}/ship")
    public ResponseEntity<Boolean> shipOrderById(@PathVariable("id") int orderId) {
        if(service.shipOrderById(orderId)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(true);
    }

}
