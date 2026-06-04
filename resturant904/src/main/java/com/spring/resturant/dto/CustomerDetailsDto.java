package com.spring.resturant.dto;

import com.spring.resturant.model.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsDto {

    private Long id;

    @Size(min = 3, max = 50, message = "name.size.invalid")
    private String name;

    @Email(message = "email.invalid")
    @Size(max = 100, message = "email.too.long")
    private String email;

    @Pattern(
            regexp = "^01[0-2,5]{1}[0-9]{8}$",
            message = "رقم الهاتف غير صحيح (يجب أن يكون رقم مصري مثل 010xxxxxxxx)"
    )
    private String phone;

    @Min(value = 0, message = "age.invalid")
    @Max(value = 120, message = "age.invalid")
    private Integer age;

    @Size(max = 255, message = "address.too.long")
    private String address;

    private String imagePath;

}
