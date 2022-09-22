package com.example.repos;

import com.example.entity.Order;
import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;


public interface OrdersRepos  extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders o WHERE o.orderstatus = 'Pending'", nativeQuery = true)
    List<Order> findPendingOrder();



    @Query(value = "SELECT * " +
            " FROM products p " +
            " INNER JOIN orderitems os ON p.upc = os.upc " +
            " WHERE os.orderid = :orderId", nativeQuery = true)
    List<Product>findItems(@Param("orderId") Integer orderId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET orderstatus = :status WHERE orderid = :orderId", nativeQuery = true)
    void updateStatus(@Param ("status") String status, @Param ("orderId") Integer id);
}
