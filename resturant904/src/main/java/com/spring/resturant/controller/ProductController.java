package com.spring.resturant.controller;

import com.spring.resturant.controller.vm.ProductsResponse;
import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProductsByCategoryId/{categoryId}/pageNum/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<ProductsResponse> getProducts(@PathVariable Long categoryId, @PathVariable int pageNumber, @PathVariable int pageSize) throws NotFound, Positive {
        return ResponseEntity.ok(productService.getAllProductsByCategoryId(categoryId, pageNumber, pageSize));
    }

    @GetMapping("/getAllProducts/pageNum/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<ProductsResponse> getAllProducts(@PathVariable int pageNumber, @PathVariable int pageSize) throws NotFound, Positive {
        ProductsResponse productsPage = productService.getAllProducts(pageNumber, pageSize);
        return ResponseEntity.ok(productsPage);
    }

    @PostMapping(value = "/saveProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> saveProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) MultipartFile image

    ) throws IOException, NotFound, IdMustNull, Required {
        productService.addProduct(name,description, Double.valueOf(price),categoryName,image);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveProducts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> saveProducts(@RequestBody @Valid List<ProductDto> productDtos) throws NotFound, IdMustNull, Required {
        productService.addListProducts(productDtos);
        return ResponseEntity.created(URI.create("/product/saveProducts")).build();
    }

    @PutMapping("/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDto productDto) throws Required, IdMustNull, NotFound {
        productService.updateProduct(productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) throws NotFound, Positive {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchProduct/{keyWord}/pageNum/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<ProductsResponse> searchProduct(@PathVariable String keyWord, @PathVariable int pageNumber, @PathVariable int pageSize) throws NotFound {
        return ResponseEntity.ok(productService.searchProduct(keyWord, pageNumber, pageSize));
    }

    @PatchMapping(value = "/patchupdateProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> patchupdateProduct(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String price,
            @RequestParam(required = false)
            MultipartFile image

    ) throws IOException, Required, IdMustNull, NotFound {

        productService
                .patchupdateProduct(
                        Long.valueOf(id),
                        name,
                        description,
                        Double.valueOf(price),
                        image
                );

        return ResponseEntity.ok().build();
    }

}
