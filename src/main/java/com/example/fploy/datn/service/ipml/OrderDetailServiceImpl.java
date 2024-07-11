package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.OrderDetail;
import com.example.fploy.datn.repository.OrderDetailRepository;
import com.example.fploy.datn.service.OrderDetailService;
import com.example.fploy.datn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(Integer id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return orderDetail.orElseThrow(() -> new IllegalArgumentException("OrderDetail not found"));
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {//lấy chi tiết đơn hàng theo Id đơn hàng
        return orderDetailRepository.findAll().stream()
                .filter(orderDetail -> orderDetail.getOrder().getId().equals(orderId))
                .toList();
    }
}
