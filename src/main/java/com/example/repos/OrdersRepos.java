package com.example.repos;

import com.example.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepos  extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * " +
            "FROM orders" +
            "WHERE OrderStatus = 'Pending'", nativeQuery = true)
    List<Orders> findPendingOrder();
}
