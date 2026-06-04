package com.spring.resturant.service;

import com.spring.resturant.dto.CustomerDetailsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerDetailsService {
    void addCustmoerDetails(
            Long customerId,
            String name,
            String email,
            String phone,
            Integer age,
            String address,
            MultipartFile image
    ) throws IOException;

    void updateCustomerDetails(
            Long customerId,
            String name,
            String email,
            String phone,
            Integer age,
            String address,
            MultipartFile image
    ) throws IOException;

    CustomerDetailsDto getCustmoerDetails(Long customerId);

}
