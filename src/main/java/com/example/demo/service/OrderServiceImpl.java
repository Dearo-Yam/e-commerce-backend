package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Pulled from Chuang's work
    @Override
    public List<Orders> getPendingOrders() {
        //Gather all the entries for department from the database and return as a list
        try{
            List<Orders> oList = repo.findPendingOrder();
            if(!oList.isEmpty())
                return oList;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


//    @Override
//    public ResponseEntity<String> update(int id, String status) {
//        try{
//            repo.updateStatus(status, id);
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


//    @Override
//    public List<Orders> getItems(int orderId) {
//        try {
//            List<Orders> iList = repo.findItems(orderId);
//            if (!iList.isEmpty())
//                return iList;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }

    // Pulled from Edwin's work
    @Override
    public int getTotalOrdersShipped() {
        try {
            return repo.getTotalOrdersShipped();
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

	@Override
	public double getAvgTimeToShip() {
		try {
			double avgTime = Double.parseDouble(String.format("%.2f", repo.getAvgTimeToShip()));
			return avgTime;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return -1;
	}
    
}
