package com.spring.resturant.controller.vm;

import com.spring.resturant.dto.OrderDto;
import com.spring.resturant.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private List<OrderDto> orders;

    private Long totalorders;

}
