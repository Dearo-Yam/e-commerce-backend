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


    // Updates Order row's Status to Shipped and DateShipped to the current Date/Time
    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders SET Orders.DateShipped = NOW(), "+
            "Orders.OrderStatus = \"Shipped\" WHERE Orders.OrderId = :orderId ;",
            nativeQuery = true)
    void shipOrderById(@Param("orderId") int orderId);

    @Query(value = "SELECT *" +
            " FROM products p " +
            " INNER JOIN orderitems os ON p.upc = os.upc " +
            " WHERE os.orderid = :orderId", nativeQuery = true)
    List<Product>findItems(@Param("orderId") Integer orderId);



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

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET orderstatus = :status WHERE orderid = :orderId", nativeQuery = true)
    void updateStatus(@Param ("status") String status, @Param ("orderId") Integer id);
}
