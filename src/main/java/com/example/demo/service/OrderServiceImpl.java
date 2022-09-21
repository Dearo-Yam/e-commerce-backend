package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repo;

    @Override
    public List<Order> getAllOrders() {
        //Gather all the entries for department from the database and return as a list
        try {
            List<Order> orderList = repo.findAll();
            if(!orderList.isEmpty())
                return orderList;
        } catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        try {
            Optional<Order> order = repo.findById(orderId);
            if(order.isPresent())
                return order;
        } catch(Exception exc) {
            System.out.println(exc);
        }
        return Optional.empty();
    }

    @Override
    public boolean shipOrderById(int orderId) {
        repo.shipOrderById(orderId);
        return repo.findById(orderId).get().getOrderStatus().equals("Delivered");
    }
}
