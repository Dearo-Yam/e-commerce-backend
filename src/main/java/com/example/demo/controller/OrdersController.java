package com.example.demo.controller;

import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
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
    public List<Orders> getAllOrders() {
        return service.getAllOrders();
    }

    // Getting a single Order by ID:
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") int orderId) {
        Optional<Orders> foundOrder = service.getOrderById(orderId);
        if(foundOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(foundOrder.get());
        }
        System.out.println("ERROR: Order with ID: " + orderId + " does not exist!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Check for authentication?
    @PutMapping("/{id}/ship")
    public ResponseEntity<Boolean> shipOrderById(@PathVariable("id") int orderId) {
        if(service.shipOrderById(orderId)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(true);
    }

    // Viewing Total Orders Shipped - Edwin
    // Renamed to "/shipped" -> /api/orders/shipped
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shipped")
    @ResponseBody
    public ResponseEntity<Integer> getOrdersShipped() {
        int numOfShipped = service.getTotalOrdersShipped();
        if(numOfShipped == -1) {
            return new ResponseEntity<>(numOfShipped, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(numOfShipped, HttpStatus.OK);
    }

}
