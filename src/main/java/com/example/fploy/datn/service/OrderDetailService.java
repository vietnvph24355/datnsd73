package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail createOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(Integer id);

    List<OrderDetail> getAllOrderDetails();

    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);
}
