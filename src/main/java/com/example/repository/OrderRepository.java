package com.example.repository;

import com.example.model.Orders;
import com.example.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT * FROM Orders o WHERE o.order_status = 'Shipped' OR o.order_status = 'Pending'",
    nativeQuery = true)
    List<Orders> findAllOrders();

    @Query(value = "SELECT * FROM orders o WHERE o.order_status = 'Pending'", nativeQuery = true)
    List<Orders> findPendingOrders();

    @Query(value = "SELECT * FROM orders o WHERE o.order_status = 'Shipped'", nativeQuery = true)
    List<Orders> findShippedOrders();

    // Updates Order row's Status to Shipped and DateShipped to the current Date/Time
    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders SET Orders.Date_Shipped = NOW(), "+
            "Orders.order_status = \"Shipped\" WHERE Orders.Order_Id = :orderId ;",
    nativeQuery = true)
    void shipOrderById(@Param("orderId") int orderId);

    // Updates Product(s) in Order to have ShippedStock incremented by the quantity ordered
    // and decrements the ReservedStock by the quantity ordered
    @Transactional
    @Modifying
    @Query(value = "UPDATE Products " +
            "JOIN Order_Items ON Order_Items.UPC = Products.UPC " +
            "JOIN Orders ON Orders.Order_Id = Order_Items.Order_Id " +
            "SET Products.Shipped_Stock = Products.Shipped_Stock + OrderItems.Quantity, " +
            "Products.Reserved_Stock = Products.Reserved_Stock - OrderItems.Quantity " +
            "WHERE Orders.order_Id = :orderId ;",
    nativeQuery = true)
    void updateStockById(@Param("orderId") int orderId);

    // Getting count of all Orders with Status = "Shipped" - Edwin
    @Query("select count(*) from Orders o where o.order_status = 'Shipped'")
    int getTotalOrdersShipped();

    @Query(value = "SELECT *" +
            " FROM products p " +
            " INNER JOIN order_items os ON p.upc = os.upc " +
            " WHERE os.order_Id = :orderId", nativeQuery = true)
    List<Products>findItems(@Param("orderId") Integer orderId);
    
    @Query("select AVG(DATEDIFF(Date_Shipped, Date_Ordered)) from Orders where order_status = 'Shipped' OR order_status = 'Delivered'")
    double getAvgTimeToShip();

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET order_status = :status WHERE order_id = :orderId", nativeQuery = true)
    void updateStatus(@Param ("status") String status, @Param ("orderId") Integer id);
}
