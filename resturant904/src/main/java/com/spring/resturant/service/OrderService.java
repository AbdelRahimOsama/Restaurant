package com.spring.resturant.service;

import com.spring.resturant.controller.vm.OrderResponse;
import com.spring.resturant.controller.vm.RequestOrderVm;
import com.spring.resturant.dto.OrderDto;
import com.spring.resturant.dto.OrderItemDto;

import java.util.List;

public interface OrderService {
    void addOrders(List<OrderItemDto> orders, Long customerId, Long totalNumber, Double totalPrice, Long tableNumber);
    OrderResponse getOrdersByCustomerId(Long customerId,Integer pageNumber, Integer pageSize);
    OrderResponse getAllOrders(Integer pageNumber, Integer pageSize);
}
