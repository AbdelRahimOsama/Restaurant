package com.spring.resturant.controller.vm;

import com.spring.resturant.dto.RoleDto;
import com.spring.resturant.repo.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponseVm {

    private Long id;

    private List<RoleDto> roleDtos;

    private String username;

    private String token;
}
