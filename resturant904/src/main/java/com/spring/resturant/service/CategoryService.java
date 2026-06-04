package com.spring.resturant.service;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;

import java.util.List;

public interface CategoryService {
    void  addCategory(CategoryDto categoryDto)throws NotFound;
    List<CategoryDto> getAllCategories();
    void updateCategory(CategoryDto categoryDto)throws  NotFound, Required;
    void deleteCategory(Long id) throws NotFound, Positive;
    void addListCategories(List<CategoryDto> categoryDtos);
}
