package com.spring.resturant.mapper;


import com.spring.resturant.dto.RoleDto;
import com.spring.resturant.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleDto toRoleDto(Role role);
    List<RoleDto> toRoleDto(List<Role> roles);

    Role toRole(RoleDto roleDto);
    List<Role> toRole(List<RoleDto> roleDtos);
}
