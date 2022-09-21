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
    // Updates Order row's Status to Shipped and DateShipped to the current Date/Time
    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders SET Orders.DateShipped = NOW(), "+
            "Orders.OrderStatus = \"Shipped\" WHERE Orders.OrderId = :orderId ;",
    nativeQuery = true)
    void shipOrderById(@Param("orderId") int orderId);

    // Updates Product(s) in Order to have ShippedStock incremented by the quantity ordered
    // and decrements the ReservedStock by the quantity ordered
    @Transactional
    @Modifying
    @Query(value = "UPDATE Products " +
            "JOIN OrderItems ON OrderItems.UPC = Products.UPC " +
            "JOIN Orders ON Orders.OrderId = OrderItems.OrderId " +
            "SET Products.ShippedStock = Products.ShippedStock + OrderItems.Quantity, " +
            "Products.ReservedStock = Products.ReservedStock - OrderItems.Quantity " +
            "WHERE Orders.orderId = :orderId ;",
    nativeQuery = true)
    void updateStockById(@Param("orderId") int orderId);
}
