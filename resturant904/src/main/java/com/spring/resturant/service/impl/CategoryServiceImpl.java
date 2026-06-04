package com.spring.resturant.service.impl;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;
import com.spring.resturant.mapper.CategoryMapper;
import com.spring.resturant.model.Category;
import com.spring.resturant.repo.CategoryRepo;
import com.spring.resturant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo) {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void addCategory(CategoryDto categoryDto)throws NotFound {
          Category category = categoryRepo.findByName(categoryDto.getName());
          if(Objects.nonNull(category)){
              throw new NotFound("category.is.found");
          }
          categoryRepo.save(categoryMapper.toCategory(categoryDto));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.toCategoryDto(categoryRepo.findAll());
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) throws NotFound,Required{
          if(Objects.isNull(categoryDto.getId())){
              throw new Required("id.required.category");
          }
          Optional<Category> category = categoryRepo.findById(categoryDto.getId());
          if(category.isEmpty()){
              throw new NotFound("Not.Found.Category");
          }
          Category category1=categoryRepo.findByName(categoryDto.getName());
          if(Objects.nonNull(category1) && !category1.getName().equals(category.get().getName())){
              throw new NotFound("category.is.found");
          }
          categoryRepo.save(categoryMapper.toCategory(categoryDto));
    }

    @Override
    public void deleteCategory(Long id)throws NotFound ,Positive {
        validateId(id);

        Optional<Category> category = categoryRepo.findById(id);
        if(category.isEmpty()){
            throw new NotFound("Not.Found.Category");
        }
        categoryRepo.deleteById(id);
    }

    private void validateId(Long id) throws Positive {
        if (id <= 0) {
            throw new Positive("Id.must.be.positive");
        }
    }

    @Override
    public void addListCategories(List<CategoryDto> categoryDtos) {

    }
}
