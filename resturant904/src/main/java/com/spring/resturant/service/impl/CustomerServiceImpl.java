package com.spring.resturant.service.impl;

import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.RoleDto;
import com.spring.resturant.enums.RoleEnum;
import com.spring.resturant.mapper.CustomerMapper;
import com.spring.resturant.model.Customer;
import com.spring.resturant.repo.CustomerRepo;
import com.spring.resturant.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo,
                               PasswordEncoder passwordEncoder,
                               CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getEmployeeByUserName(String userName)
            throws Found, NotFound {

        if (userName == null || userName.isBlank()) {
            throw new Found("username.required");
        }

        Customer customer = customerRepo.findByUsername(userName)
                .orElseThrow(() -> new NotFound("user.not.exist"));

        return customerMapper.toEmployeeDto(customer);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) throws Found {

        if (customerDto.getUsername() == null || customerDto.getUsername().isBlank()) {
            throw new Found("username.required");
        }

        if (customerDto.getPassword() == null || customerDto.getPassword().isBlank()) {
            throw new Found("password.required");
        }

        if (customerRepo.findByUsername(customerDto.getUsername()).isPresent()) {
            throw new Found("username.already.exist");
        }

        // 🔐 encode password
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        // 🎭 default role
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(RoleEnum.USER.getRoleName());
        roleDto.setCustomerDto(customerDto);

        customerDto.setRoleDtos(List.of(roleDto));

        Customer customer = customerMapper.toEmployee(customerDto);
        customer.getRoles().forEach(r -> r.setCustomer(customer));
        Customer saved = customerRepo.save(customer);

        return customerMapper.toEmployeeDto(saved);
    }
}