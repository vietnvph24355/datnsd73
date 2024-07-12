package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.Order;
import com.example.fploy.datn.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order, List<OrderDetail> orderDetails);

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void updateOrderStatus(Integer id, Integer status);
}
