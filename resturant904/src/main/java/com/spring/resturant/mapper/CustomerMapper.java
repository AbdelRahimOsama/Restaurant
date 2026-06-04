package com.spring.resturant.mapper;

import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "roles",target ="roleDtos")
    CustomerDto toEmployeeDto(Customer customer);

    @Mapping(source = "roleDtos",target ="roles")
    Customer toEmployee(CustomerDto customerDto);

}
