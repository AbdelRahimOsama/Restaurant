package com.spring.resturant.dto;

import com.spring.resturant.model.Customer;
import com.spring.resturant.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Long tableNumber;

    private String code;

    private Double totalPrice;

    private Long totalNumber;

    private LocalDateTime atdate;

    private List<OrderItemDto> orderItems;

    private Long customerId;

    private String customerName;
    //private Customer customer;

}
