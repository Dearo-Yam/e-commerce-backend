package com.example.service;

import com.example.entity.Orders;
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
    public List<Orders> getPendingOrders() {
        //Gather all the entries for department from the database and return as a list
        try{
            List<Orders> oList = repo.findPendingOrder();
            if(!oList.isEmpty())
                return oList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }


    @Override
    public ResponseEntity<String> update(int id,String status) {
        Optional<Orders> order = repo.findById(id);
        if(order.isPresent())
        {
            order.get().setOrderStatus(status);
            repo.save(order.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
