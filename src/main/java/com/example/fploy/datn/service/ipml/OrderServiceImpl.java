package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.Order;
import com.example.fploy.datn.entity.OrderDetail;
import com.example.fploy.datn.repository.OrderDetailRepository;
import com.example.fploy.datn.repository.OrderRepository;
import com.example.fploy.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

    @Service
    public class OrderServiceImpl implements OrderService {

        @Autowired
        private OrderRepository orderRepository;
        @Autowired
        private OrderDetailRepository orderDetailRepository;

        @Override
        public Order createOrder(Order order, List<OrderDetail> orderDetails) {
            order.setCreateAt(new Date(System.currentTimeMillis()));
            order.setStatus(0); // mac dinh la 0
            Order savedOrder = orderRepository.save(order);

            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.setOrder(savedOrder);
                orderDetailRepository.save(orderDetail);
            }
            return savedOrder;
        }

        @Override
        public Order getOrderById(Integer id) {
            Optional<Order> order = orderRepository.findById(id);
            return order.orElseThrow(() -> new IllegalArgumentException("Hóa Đơn Không Tồn Tại"));
        }

        @Override
        public List<Order> getAllOrders() {
            return orderRepository.findAll();
        }

        @Override
        public void updateOrderStatus(Integer id, Integer status) {
            Order order = getOrderById(id);
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

