package com.example.repos;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepos extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products p JOIN orderitems os ON" +
            " p.upc = os.upc WHERE os.orderid = :orderId", nativeQuery = true)
    List<Product> findItems(@Param("orderId") int id);
}
