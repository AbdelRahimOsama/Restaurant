package com.spring.resturant.controller;

import com.spring.resturant.dto.CustomerDetailsDto;
import com.spring.resturant.service.CustomerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/CustomerDetails")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class CustomerDetailsController {

    final private CustomerDetailsService customerDetailsService;

    @PostMapping(value = "add/{customerId}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addCustomerDetails(
            @PathVariable Long customerId,

            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String address,

            @RequestParam(required = false)
            MultipartFile image
    )throws IOException{
        customerDetailsService
                .addCustmoerDetails(
                        customerId,
                        name,
                        email,
                        phone,
                        age,
                        address,
                        image
                );

        return ResponseEntity.ok().build();
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@PathVariable Long customerId) {
        CustomerDetailsDto customerDetailsDto = customerDetailsService.getCustmoerDetails(customerId);
        return ResponseEntity.ok(customerDetailsDto);
    }

    @PatchMapping(value = "update/{customerId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCustomerDetails(

            @PathVariable Long customerId,

            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String address,

            @RequestParam(required = false)
            MultipartFile image

    ) throws IOException {

        customerDetailsService
                .updateCustomerDetails(
                        customerId,
                        name,
                        email,
                        phone,
                        age,
                        address,
                        image
                );

        return ResponseEntity.ok().build();
    }
}
