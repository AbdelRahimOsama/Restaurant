package com.spring.resturant.service.impl;

import com.spring.resturant.controller.vm.ProductsResponse;
import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.mapper.ProductMapper;
import com.spring.resturant.model.Category;
import com.spring.resturant.model.Product;
import com.spring.resturant.repo.CategoryRepo;
import com.spring.resturant.repo.ProductRepo;
import com.spring.resturant.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepo productRepo;
    private final ProductMapper  productMapper;
    private final CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ProductsResponse getAllProductsByCategoryId(Long categoryId,int pageNumber,int pageSize) throws Positive ,NotFound{
        validateId(categoryId);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Product> productPage = productRepo.findByCategoryId(categoryId,pageable);
        if(productPage.isEmpty()){
            throw new NotFound("Not.Found.product.InCategory");
        }
        List<ProductDto> productDtos = productMapper.toProductDto(productPage.getContent());
        return new ProductsResponse(
                productDtos,                      // prducts
                productPage.getTotalElements()    // totalproducts
        );
    }

    @Override
    public ProductsResponse getAllProducts(int pageNumber, int pageSize) throws Positive {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Product> productPage = productRepo.findAll(pageable);
        List<ProductDto> productDtos = productMapper.toProductDto(productPage.getContent());

        return new ProductsResponse(
                productDtos,                      // prducts
                productPage.getTotalElements()    // totalproducts
        );
    }

    @Override
    public ProductsResponse searchProduct(String keyWord,int pageNumber, int pageSize)throws NotFound  {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Product> products =productRepo.search(keyWord,pageable);
        if(products.isEmpty()){
            throw new NotFound("Not.Found.product");
        }
        List<ProductDto> productDtos = productMapper.toProductDto(products.getContent());
        try {
            return new ProductsResponse(
                    productDtos,
                    products.getTotalElements()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void patchupdateProduct(Long id,String name, String description, Double price, MultipartFile image) throws IOException, NotFound, IdMustNull, Required {
        Product product = productRepo.findById(id).get();

        if(name!=null && !name.isEmpty())
            product.setName(name);

        if(description!=null && !description.isEmpty())
            product.setDescription(description);

        if(price!=null)
            product.setPrice(price);

        // IMAGE
        if (image != null && !image.isEmpty()) {

            String fileName =
                    System.currentTimeMillis()
                            + "_" +
                            image.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(
                    image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            product.setImagePath(
                    "uploads/" + fileName
            );
        }

        productRepo.save(product);
    }
    
    @Override
    public void addProduct(String name,
                           String description,
                           Double price,
                           String categoryName,
                           MultipartFile image) throws NotFound, IdMustNull, Required,IOException{
        Product product = new Product();
            Category category = categoryRepo.findByName(categoryName);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
        // IMAGE
        if (image != null && !image.isEmpty()) {
            String fileName =
                    System.currentTimeMillis()
                            + "_" +
                            image.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(
                    image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );
            product.setImagePath(
                    "uploads/" + fileName
            );
        }
        productRepo.save(product);
    }

    @Override
    public void addListProducts(List<ProductDto> products) throws NotFound, IdMustNull, Required {
        List<Product> productEntities = new ArrayList<>();

        for (ProductDto productDto : products) {
            productEntities.add(convertToEntity(productDto,"ADD"));
        }
        productRepo.saveAll(productEntities);
    }


    @Override
    public void updateProduct(ProductDto productDto) throws Required, IdMustNull, NotFound {

        if(Objects.isNull(productDto.getId())){
            throw new Required("id.required.product");
        }
        productRepo.save(convertToEntity(productDto,"UPDATE"));
    }

    @Override
    public void deleteProduct(Long productId)throws NotFound, Positive {
        validateId(productId);
        Optional<Product> product = productRepo.findById(productId);
        if (product.isEmpty()) {
            throw new NotFound("Not.Found.product");
        }
        productRepo.deleteById(productId);
    }



    private void validateId(Long id) throws Positive {

        if (id <= 0) {
            throw new Positive("Id.must.be.positive");
        }
    }

    private Product convertToEntity(ProductDto productDto, String process) throws NotFound, IdMustNull, Required {
        validateProcess(productDto, process);
        validateProductFields(productDto);
        Category category = categoryRepo.findByName(productDto.getCategoryName());
        if (Objects.isNull(category)) {
            throw new NotFound("Not.Found.Category");
        }
        Product product = productMapper.toProduct(productDto);
        product.setCategory(category);
        return product;
    }

    private void validateProcess(ProductDto productDto, String process) throws IdMustNull, NotFound {

        if (process.equals("ADD")) {
            if (Objects.nonNull(productDto.getId())) {
                throw new IdMustNull("error.id.null");
            }
            Product product = productRepo.findByName(productDto.getName());
            if (Objects.nonNull(product)) {
                throw new NotFound("product.is.found");
            }
        }
        if (process.equals("UPDATE")) {
            Optional<Product> product = productRepo.findById(productDto.getId());
            if (product.isEmpty()) {
                throw new NotFound("Not.Found.product");
            }
            Product product1 = productRepo.findByName(productDto.getName());
            if (Objects.nonNull(product1) && !product1.getName().equals(product.get().getName())) {
                throw new NotFound("product.is.found");
            }
        }
    }

    private void validateProductFields(ProductDto productDto) throws Required {

        if (Objects.isNull(productDto.getName())) {
            throw new Required("name.required.product");
        }

        if (Objects.isNull(productDto.getCategoryName())) {
            throw new Required("name.required.category");
        }
    }


}
