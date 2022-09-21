package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	@Autowired
	OrdersService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/totalshipped")
	@ResponseBody
	public ResponseEntity<Integer> getOrdersShipped() {
		int numOfShipped = service.getTotalOrdersShipped();
		if(numOfShipped == -1) {
			return new ResponseEntity<>(numOfShipped, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<>(numOfShipped, HttpStatus.OK);
		}
	}
}
