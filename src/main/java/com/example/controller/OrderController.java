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
@RequestMapping("/api/orders")
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
    public List<Product> getAllItems(@PathVariable("id") Integer orderId){
        return service.getItems(orderId);
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
