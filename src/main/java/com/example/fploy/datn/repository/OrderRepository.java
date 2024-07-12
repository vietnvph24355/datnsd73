package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
