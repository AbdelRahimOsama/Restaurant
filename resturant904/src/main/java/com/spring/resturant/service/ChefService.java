package com.spring.resturant.service;

import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.model.Chef;

import java.util.List;

public interface ChefService {
     List<ChefDto> getChefs();
}
