package com.spring.resturant.controller.vm;

import com.spring.resturant.dto.OrderItemDto;
import com.spring.resturant.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderVm {

    private List<OrderItemDto> orderItemDtos;
    private Long customerId;
    private Long totalNumber;
    private Double totalPrice;
    private Long tableNumber;
}
