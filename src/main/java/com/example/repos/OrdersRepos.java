package com.example.repos;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepos  extends JpaRepository<Order, Integer> {
}
