package com.example.service;

import com.example.entity.Order;
import com.example.repos.OrdersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImple implements OrderService {

    @Autowired
    private OrdersRepos repo;

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
    public ResponseEntity<String> update(int id,String status) {
        try{
            repo.updateStatus(status, id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(Exception exc){
            System.out.println(exc);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public List<Order> getItems(int orderId) {
        try {
            List<Order> iList = repo.findItems(orderId);
            if (!iList.isEmpty())
                return iList;
        } catch (Exception exc) {
            System.out.println(exc);
        }
        return null;
    }

}
