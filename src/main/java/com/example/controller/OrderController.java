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

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/show")
    @ResponseBody
    public List<Order> getOrders() {
        //Gather all the entries for department from the database and return as a list
        return service.getPendingOrders();
    }

    @GetMapping("/items/{id}")
    @ResponseBody
    public List<Product> getAllItems(@PathVariable Integer orderId){
        return service.getItems(orderId);
    }



    @PutMapping("/update/{id}/{status}")
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable int id, @PathVariable String status)
    {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try{
            if(status != null)
                return service.update(id, status);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
