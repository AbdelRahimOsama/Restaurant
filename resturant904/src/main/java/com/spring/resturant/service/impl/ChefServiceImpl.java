package com.spring.resturant.service.impl;

import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.mapper.ChefMapper;
import com.spring.resturant.model.Chef;
import com.spring.resturant.repo.ChefRepo;
import com.spring.resturant.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    final private ChefRepo chefRepo;
    final private ChefMapper chefMapper;

    public ChefServiceImpl(ChefRepo chefRepo, ChefMapper chefMapper) {
        this.chefRepo = chefRepo;
        this.chefMapper = chefMapper;
    }

    @Override
    public List<ChefDto> getChefs() {
        return chefMapper.toChefDto((List<Chef>) chefRepo.findAll());
    }
}
