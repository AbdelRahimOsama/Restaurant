package com.spring.resturant.repo;

import com.spring.resturant.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUsername(String userName);
}
