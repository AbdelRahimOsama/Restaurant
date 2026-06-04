package com.spring.resturant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {

    private Long id;

    @NotBlank(message = "name.required")
    @Size(min = 3, max = 50, message = "name.size.invalid")
    private String name ;

    @NotBlank(message = "email.required")
    @Email(message = "email.invalid")
    @Size(max = 100, message = "email.too.long")
    private String email;

    @Size(max = 100, message = "subject.too.long")
    private String subject;

    @NotBlank(message = "message.required")
    @Size(min = 10, max = 1000, message = "message.size.invalid")
    private String message;

    private CustomerDto customerDto;

}
