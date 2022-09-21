package com.example.demo.service;

import com.example.demo.model.Orders;
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
    public List<Orders> getAllOrders() {
        //Gather all the entries for department from the database and return as a list
        try {
            List<Orders> orderList = repo.findAll();
            if(!orderList.isEmpty())
                return orderList;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Optional<Orders> getOrderById(int orderId) {
        try {
            Optional<Orders> order = repo.findById(orderId);
            if(order.isPresent())
                return order;
        } catch(Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean shipOrderById(int orderId) {
        repo.shipOrderById(orderId);
        repo.updateStockById(orderId);
        return repo.findById(orderId).get().getOrderStatus().equals("Delivered");
    }



    @Override
    public int getTotalOrdersShipped() {
        try {
            return repo.getTotalOrdersShipped();
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }
}
