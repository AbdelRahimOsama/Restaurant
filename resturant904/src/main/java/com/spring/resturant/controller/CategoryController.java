package com.spring.resturant.controller;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.dto.Exception.Positive;
import com.spring.resturant.dto.Exception.Required;
import com.spring.resturant.model.Category;
import com.spring.resturant.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addCategory(@RequestBody @Valid CategoryDto categoryDto) throws NotFound {
        categoryService.addCategory(categoryDto);
        return ResponseEntity.created(URI.create("/category/addCategory")).build();
    }

    @GetMapping("/allCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/updateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateCategory(@RequestBody @Valid CategoryDto categoryDto) throws NotFound , Required {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@RequestParam Long id) throws NotFound, Positive {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
