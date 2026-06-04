package com.spring.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;

    @NotBlank(message = "invalid.name")
    private String name;

    private String logo;

    private String flag;

    //@Valid
    //private List<ProductDto> products;

}
