package com.example.service;

import com.example.entity.Order;
import com.example.repos.OrdersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;

public class OrdersServiceImple implements OrdersService{

    @Autowired
    private OrdersRepos repo;

    @Override
    @GetMapping
    public List<Order> getAll() {
        //Gather all the entries for department from the database and return as a list
        try{
            List<Order> oList = repo.findAll();
            if(!oList.isEmpty())
                return oList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    @PatchMapping
    public ResponseEntity<String> update(Order r, int id) {
        return null;
    }

    @Override
    public Optional<Order> getSaleById(int id) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> updateById(int id, String email) {
    return null;
    }
}
