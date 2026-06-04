package com.spring.resturant.mapper;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDto(List<Category> category);

    @Mapping(target = "products",ignore = true)
    Category toCategory(CategoryDto categoryDto);

    List<Category> toCategory(List<CategoryDto> categoryDto);

}
