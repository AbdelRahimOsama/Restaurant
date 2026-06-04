package com.spring.resturant.mapper;

import com.spring.resturant.dto.OrderItemDto;
import com.spring.resturant.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    @Mapping(target = "order", ignore = true)
    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

    List<OrderItemDto> orderItemToOrderItemDto(List<OrderItem> orderItems);

    List<OrderItem> orderItemDtoToOrderItem(List<OrderItemDto> orderItemDtos);
}