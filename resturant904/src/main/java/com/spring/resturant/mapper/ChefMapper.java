package com.spring.resturant.mapper;

import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.model.Chef;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    ChefDto toChefDto(Chef chef);

    List<ChefDto> toChefDto(List<Chef> Chef);

    Chef toChef(ChefDto ChefDto);

    List<Chef> toChef(List<ChefDto> ChefDto);
}
