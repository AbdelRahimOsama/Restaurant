package com.spring.resturant.service;

import com.spring.resturant.controller.vm.TokenResponseVm;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import jakarta.transaction.SystemException;


public interface CustomerService {

    CustomerDto getEmployeeByUserName(String userName) throws Found, NotFound ;

    CustomerDto addCustomer(CustomerDto customerDto) throws Found;
}
