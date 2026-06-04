package com.spring.resturant.service.impl;

import com.spring.resturant.config.TokenHandler;
import com.spring.resturant.controller.vm.TokenResponseVm;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.service.AuthService;
import com.spring.resturant.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerService customerService;
    private final TokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(CustomerService customerService,
                           TokenHandler tokenHandler,
                           PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponseVm signUp(CustomerDto customerDto) throws Found {

        CustomerDto saved = customerService.addCustomer(customerDto);

        String token = tokenHandler.generateToken(saved);

        return new TokenResponseVm(
                saved.getId(),
                saved.getRoleDtos(),
                saved.getUsername(),
                token
        );
    }

    @Override
    public TokenResponseVm login(CustomerDto customerDto)
            throws Found, NotFound {

        if (customerDto.getUsername() == null || customerDto.getUsername().isBlank()) {
            throw new Found("username.required");
        }

        if (customerDto.getPassword() == null || customerDto.getPassword().isBlank()) {
            throw new Found("password.required");
        }

        CustomerDto user = customerService.getEmployeeByUserName(customerDto.getUsername());

        // 🔐 check password
        if (!passwordEncoder.matches(customerDto.getPassword(), user.getPassword())) {
            throw new NotFound("invalid.credentials");
        }

        String token = tokenHandler.generateToken(user);

        return new TokenResponseVm(
                user.getId(),
                user.getRoleDtos(),
                user.getUsername(),
                token
        );
    }
}