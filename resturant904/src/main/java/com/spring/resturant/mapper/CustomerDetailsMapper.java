package com.spring.resturant.mapper;

import com.spring.resturant.dto.CustomerDetailsDto;
import com.spring.resturant.model.CustomerDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDetailsMapper {

    @Mapping(target = "customer",ignore = true)
    CustomerDetails customerDetailsDtoToCustomerDetails(CustomerDetailsDto customerDetailsDto);

    CustomerDetailsDto customerDetailsToCustomerDetailsDto(CustomerDetails customerDetails);
}
