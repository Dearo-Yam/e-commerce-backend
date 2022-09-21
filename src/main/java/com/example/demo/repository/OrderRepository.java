package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders SET DateShipped = NOW(), OrderStatus = \"Shipped\" WHERE OrderId = :orderId",
    nativeQuery = true)
    void shipOrderById(@Param("orderId") int orderId);
}
