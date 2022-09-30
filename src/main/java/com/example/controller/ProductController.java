package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.model.*;
import com.example.service.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class ProductController {
    @Autowired
    OrderService service;

    @Autowired
    KafkaTemplate <String, Orders> kafkaTemplate;

   
    @PatchMapping("/{id}/ship")
    public ResponseEntity<String> shipItem(@PathVariable("id") int orderid){
        Optional<Orders> exists=service.getOrderById(orderid);
        
        if(exists.isPresent()){
            Orders order = exists.get();
            order.setOrderStatus("Shipped");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();       
            order.setDateShipped(dtf.format(now));
            kafkaTemplate.send("wms-order-shipped", order);
            return ResponseEntity.status(HttpStatus.OK).body("Order " + orderid + " updated to Shipped");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
