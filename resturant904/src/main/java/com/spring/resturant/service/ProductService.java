package com.spring.resturant.service;

import com.spring.resturant.controller.vm.ProductsResponse;
import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;
import com.spring.resturant.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void  addProduct(String name,
                      String description,
                      Double price,
                     String categoryName,
                     MultipartFile image)throws IOException, NotFound, IdMustNull, Required;
    void addListProducts(List<ProductDto> products) throws NotFound, IdMustNull, Required;
    ProductsResponse getAllProductsByCategoryId(Long CategoryId,int pageNumber,int pageSize) throws Positive,NotFound;
    ProductsResponse getAllProducts(int pageNumber, int pageSize) throws Positive ;
    void updateProduct(ProductDto productDto)throws NotFound, IdMustNull, Required;
    void deleteProduct(Long id)throws NotFound, Positive;
    ProductsResponse searchProduct(String keyWord,int pageNumber, int pageSize) throws NotFound;
    void patchupdateProduct(
            Long id,
            String name,
            String description,
            Double price,
            MultipartFile image
            )throws IOException, NotFound, IdMustNull, Required;
}
