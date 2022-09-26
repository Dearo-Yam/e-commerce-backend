package com.example.controller;

import com.example.model.Orders;
import com.example.service.OrderService;
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

    // Getting all Orders (orders marked as "Shipped" or "Pending", not "Canceled"):
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

    // Showing all Pending Orders - Chuang
    // Renamed from "/show" to "/pending"
    @GetMapping("/pending")
    @ResponseBody
    public List<Orders> getPendingOrders() {
        //Gather all the entries for department from the database and return as a list
        return service.getPendingOrders();
    }

    @GetMapping("/shipped")
    @ResponseBody
    public List<Orders> getShippedOrders() {
        return service.getShippedOrders();
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
    // Renamed to "/shipped" -> /api/orders/shipped/count
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shipped/count")
    @ResponseBody
    public ResponseEntity<Integer> getOrdersShipped() {
        int numOfShipped = service.getTotalOrdersShipped();
        if(numOfShipped == -1) {
            return new ResponseEntity<>(numOfShipped, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(numOfShipped, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/shipped/avg")
    @ResponseBody
    public ResponseEntity<Double> getAvgTimeToShip() {
    	double avgTime = service.getAvgTimeToShip();
        if(avgTime == -1) {
            return new ResponseEntity<>(avgTime, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(avgTime, HttpStatus.OK);
    }

    // Pulled from Chuang
    // Maybe check String status to be either Shipped, Pending, or Canceled?
    @PutMapping("/update/{id}/{status}")
    @ResponseBody
    public ResponseEntity<Orders> update(@PathVariable("id") int id, @PathVariable("status") String status)
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
