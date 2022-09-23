package com.example.controller;

import com.example.entity.Order;
import com.example.entity.OrderItems;
import com.example.entity.Product;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/pending")
    @ResponseBody
    public List<Order> getOrders() {
        //Gather all the entries for department from the database and return as a list
        return service.getPendingOrders();
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
    @GetMapping("/items/{id}")
    @ResponseBody
    public List<Product> getAllItems(@PathVariable("id") Integer orderId){
        return service.getItems(orderId);
    }



    // Check for authentication?
    @PutMapping("/{id}/ship")
    public ResponseEntity<Boolean> shipOrderById(@PathVariable("id") int orderId) {
        if(service.shipOrderById(orderId)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(true);
    }

    @PutMapping("/update/{id}/{status}")
    @ResponseBody
    public ResponseEntity<Order> update(@PathVariable("id") int id, @PathVariable("status") String status)
    {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try{
            if(status != null && id > 0)
                return service.update(id, status);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
