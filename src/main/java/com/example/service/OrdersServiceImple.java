package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderItems;
import com.example.entity.Product;
import com.example.repos.OrderItemsRepo;
import com.example.repos.OrdersRepos;
import com.example.repos.ProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImple implements OrderService {

    @Autowired
    private OrdersRepos repo;

    @Autowired
    private OrderItemsRepo os;

    @Autowired
    private ProductRepos ps;


    @Override
    public List<Order> getPendingOrders() {
        //Gather all the entries for department from the database and return as a list
        try{
            List<Order> oList = repo.findPendingOrder();
            if(!oList.isEmpty())
                return oList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }
    @Override
    public List<Order> getAllOrders() {
        //Gather all the entries for department from the database and return as a list
        try {
            List<Order> orderList = repo.findAll();
            if(!orderList.isEmpty())
                return orderList;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean shipOrderById(int orderId) {
        repo.shipOrderById(orderId);
        repo.updateStockById(orderId);
        return repo.findById(orderId).get().getOrderstatus().equals("Delivered");
    }


    @Override
    public Optional<Order> getOrderById(int orderId) {
        try {
            Optional<Order> order = repo.findById(orderId);
            if(order.isPresent())
                return order;
        } catch(Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
    @Override

    public ResponseEntity<Order> update(int id,String status) {
        try{
            repo.updateStatus(status, id);
            Optional<Order> order = repo.findById(id);
;            return new ResponseEntity<>(order.get(), HttpStatus.ACCEPTED);
        }catch(Exception exc){
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public List<Product> getItems(int orderId) {
        try {
            List<Product> iList = ps.findItems(orderId);
            if (!iList.isEmpty())
                return iList;
        } catch (Exception exc) {
            System.out.println(exc);
        }
        return null;
    }

}
