package com.spring.resturant.mapper;

import com.spring.resturant.dto.OrderDto;
import com.spring.resturant.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "atdate", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "customerId",ignore = true)
    @Mapping(target = "customerName",ignore = true)
    OrderDto orderToOrderDto(Order order);

    List<OrderDto> orderToOrderDto(List<Order> orders);

    List<Order> orderDtoToOrder(List<OrderDto> orderDtos);
}