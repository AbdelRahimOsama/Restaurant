package com.spring.resturant.service.impl;

import com.spring.resturant.controller.vm.OrderResponse;
import com.spring.resturant.controller.vm.RequestOrderVm;
import com.spring.resturant.dto.OrderDto;
import com.spring.resturant.dto.OrderItemDto;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.mapper.OrderMapper;
import com.spring.resturant.mapper.ProductMapper;
import com.spring.resturant.model.Customer;
import com.spring.resturant.model.Order;
import com.spring.resturant.model.OrderItem;
import com.spring.resturant.model.Product;
import com.spring.resturant.repo.CustomerRepo;
import com.spring.resturant.repo.OrderRepo;
import com.spring.resturant.repo.ProductRepo;
import com.spring.resturant.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceIml implements OrderService {

    final private OrderRepo orderRepo;
    final private OrderMapper orderMapper;
    final private ProductMapper prductMapper;
    final private CustomerRepo customerRepo;
    final private ProductRepo productRepo;

    final static private String CODE = "CODE-";

    public OrderServiceIml(OrderRepo orderRepo, OrderMapper orderMapper, ProductMapper  productMapper
            , CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.prductMapper = productMapper;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;

    }

    private OrderDto ColectInOrder(List<OrderItemDto> productDtos,Long totalNumber,Double totalPrice,Long tableNumber){
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalNumber(totalNumber);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setTableNumber(tableNumber);
        orderDto.setOrderItems(productDtos);
        return orderDto;
    }

    @Override
    public void addOrders(List<OrderItemDto> orderItemDtos,
                          Long customerId,
                          Long totalNumber,
                          Double totalPrice,
                          Long tableNumber) {

        LocalDateTime atdate = LocalDateTime.now();

        OrderDto orderDto = ColectInOrder(
                orderItemDtos,
                totalNumber,
                totalPrice,
                tableNumber
        );

        Order order = orderMapper.orderDtoToOrder(orderDto);
        order.setAtdate(atdate);
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto dto : orderItemDtos) {

            Product product = productRepo.findById(dto.getProduct().getId()).get();
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity().intValue());
            items.add(item);
        }

        order.setOrderItems(items);
        Customer customer = customerRepo.findById(customerId).get();
        order.setCode(CODE);
        order.setCustomer(customer);
        orderRepo.save(order);
        order.setCode(CODE+order.getId());
        orderRepo.save(order);
    }

    @Override
    public OrderResponse getOrdersByCustomerId(Long customerId,Integer pageNumber,Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Order> orders = orderRepo.getOrdersByCustomerId(customerId,pageable);

        return new OrderResponse(orderMapper.orderToOrderDto(orders.getContent()),orders.getTotalElements());

    }

    @Override
    public OrderResponse getAllOrders(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(
                pageNumber - 1,
                pageSize,
                Sort.by(Sort.Direction.DESC, "atdate")
        );

        Page<Order> orders = orderRepo.findAll(pageable);
        List<OrderDto> orderDtos = orderMapper.orderToOrderDto(orders.getContent());
        int length = orderDtos.size();
        for (int i = 0; i < length; i++) {
            OrderDto orderDto = orderDtos.get(i);
            orderDto.setCustomerId(orders.getContent().get(i).getCustomer().getId());
            orderDto.setCustomerName(orders.getContent().get(i).getCustomer().getUsername());
        }
        return new OrderResponse(
                orderDtos,
                orders.getTotalElements()
        );
    }
}
