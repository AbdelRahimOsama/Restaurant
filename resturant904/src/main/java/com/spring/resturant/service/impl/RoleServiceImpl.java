package com.spring.resturant.service.impl;


import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.RoleDto;
import com.spring.resturant.mapper.CategoryMapper;
import com.spring.resturant.mapper.CustomerMapper;
import com.spring.resturant.mapper.RoleMapper;
import com.spring.resturant.repo.RoleRepo;
import com.spring.resturant.service.RoleService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepo roleRepo;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    @Override
    public void add_role(RoleDto roleDto) throws IdMustNull {
        if(Objects.isNull(roleDto.getId())){
            throw new IdMustNull("error.id.null");
        }
        roleRepo.save(roleMapper.toRole(roleDto));
    }

    @Override
    public void delete_role(RoleDto roleDto) throws IdMustNull {

    }

    @Override
    public void update_role(RoleDto roleDto) throws IdMustNull {

    }


}
