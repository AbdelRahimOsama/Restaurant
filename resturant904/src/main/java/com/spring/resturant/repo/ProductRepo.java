package com.spring.resturant.repo;

import com.spring.resturant.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Product findByName(String name);

    @Query("SELECT prod FROM Product prod WHERE UPPER(prod.name) LIKE CONCAT('%', UPPER(:keyWord), '%') OR UPPER(prod.description) LIKE CONCAT('%', UPPER(:keyWord), '%')")
    Page<Product> search(@Param("keyWord") String keyWord,Pageable pageable);
}
