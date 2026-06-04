package com.spring.resturant.service;


import com.spring.resturant.controller.vm.TokenResponseVm;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import jakarta.transaction.SystemException;

public interface AuthService {

    TokenResponseVm signUp(CustomerDto customerDto) throws Found;

    TokenResponseVm login(CustomerDto customerDto) throws SystemException ,Found, NotFound;

}
