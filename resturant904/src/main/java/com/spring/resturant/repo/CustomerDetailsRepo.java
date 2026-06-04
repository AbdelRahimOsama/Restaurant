package com.spring.resturant.repo;


import com.spring.resturant.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails,Long> {

    @Query("SELECT c FROM CustomerDetails c WHERE c.customer.id = :customerId")
    CustomerDetails findByCustomerId(@Param("customerId") Long customerId);
}
