package com.spring.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.resturant.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    @Positive(message = "Id.must.be.positive")
    private Long id;

    @NotBlank(message = "invalid.name")
    private String name;

    @Size(max = 500, message = "description.too.long")
    private String description;

    @NotNull(message = "price.required")
    @Positive(message = "price.must.be.positive")
    private Double price;

    private String imagePath;

    private Long categoryId;

    private String categoryName;

    private List<OrderDto> orderDtos;
    

}
