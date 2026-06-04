package com.spring.resturant.mapper;

import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {



      @Mapping(target = "categoryId", ignore = true )
      @Mapping(target = "categoryName", ignore = true )
      ProductDto toProductDto(Product product);

      List<ProductDto> toProductDto(List<Product> product);

      @Mapping(target = "category", ignore = true)
      @Mapping(target = "orderItems", ignore = true)
      Product toProduct(ProductDto productDto);

      List<Product> toProduct(List<ProductDto> productDto);
}
