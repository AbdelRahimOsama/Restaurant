package com.spring.resturant.service;

import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.RoleDto;

public interface RoleService {
    void add_role(RoleDto roleDto) throws IdMustNull;
    void delete_role(RoleDto roleDto) throws IdMustNull;
    void update_role(RoleDto roleDto) throws IdMustNull;
}
