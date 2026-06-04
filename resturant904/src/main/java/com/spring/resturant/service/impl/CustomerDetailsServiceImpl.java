package com.spring.resturant.service.impl;

import com.spring.resturant.dto.CustomerDetailsDto;
import com.spring.resturant.mapper.CustomerDetailsMapper;
import com.spring.resturant.model.Customer;
import com.spring.resturant.model.CustomerDetails;
import com.spring.resturant.repo.CustomerDetailsRepo;
import com.spring.resturant.repo.CustomerRepo;
import com.spring.resturant.service.CustomerDetailsService;
import com.spring.resturant.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    final private CustomerDetailsMapper customerDetailsMapper;
    final private CustomerDetailsRepo customerDetailsRepo;
    final private CustomerRepo customerRepo;

    public CustomerDetailsServiceImpl(CustomerDetailsMapper customerDetailsMapper, CustomerDetailsRepo customerDetailsRepo, CustomerRepo customerRepo) {
        this.customerDetailsMapper = customerDetailsMapper;
        this.customerDetailsRepo = customerDetailsRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public void addCustmoerDetails(
            Long customerId,
            String name,
            String email,
            String phone,
            Integer age,
            String address,
            MultipartFile image
    ) throws IOException {

        CustomerDetails customerDetails = new CustomerDetails();

        customerDetails.setName(name);
        customerDetails.setEmail(email);
        customerDetails.setPhone(phone);
        customerDetails.setAge(age);
        customerDetails.setAddress(address);

        // IMAGE UPLOAD
        if (image != null && !image.isEmpty()) {

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + image.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(
                    image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            customerDetails.setImagePath("uploads/" + fileName);
        }

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerDetails.setCustomer(customer);

        customerDetailsRepo.save(customerDetails);
    }

    @Override
    public void updateCustomerDetails(
            Long customerId,
            String name,
            String email,
            String phone,
            Integer age,
            String address,
            MultipartFile image
    ) throws IOException {

        CustomerDetails customerDetails =
                customerDetailsRepo.findByCustomerId(customerId);

        if (name != null) {
            customerDetails.setName(name);
        }

        if (email != null) {
            customerDetails.setEmail(email);
        }

        if (phone != null) {
            customerDetails.setPhone(phone);
        }

        if (age != null) {
            customerDetails.setAge(age);
        }

        if (address != null) {
            customerDetails.setAddress(address);
        }

        // IMAGE
        if (image != null && !image.isEmpty()) {

            String fileName =
                    System.currentTimeMillis()
                            + "_" +
                            image.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(
                    image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            customerDetails.setImagePath(
                    "uploads/" + fileName
            );
        }

        customerDetailsRepo.save(customerDetails);
    }

    @Override
    public CustomerDetailsDto getCustmoerDetails(Long customerId) {
        CustomerDetails customerDetails = customerDetailsRepo.findByCustomerId(customerId);
        return customerDetailsMapper.customerDetailsToCustomerDetailsDto(customerDetails);
    }
}
