package com.spring.resturant.dto;

import com.spring.resturant.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @Size(min = 3, max = 20, message = "username.size")
    private String username;

    @Size(min = 6, message = "password.size")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "password.weak"
    )
    private String password;

    private List<RoleDto> roleDtos;

    private List<ContactInfoDto> contactInfoDtos;

    private List<OrderDto> orderDtos;
}