package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersRepository repo;

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
