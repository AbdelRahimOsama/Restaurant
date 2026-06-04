package com.spring.resturant.repo;

import com.spring.resturant.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("""
    select distinct o
    from Order o
    left join fetch o.orderItems oi
    left join fetch oi.product
    where o.customer.id = :customerId
    order by o.atdate desc
""")
    Page<Order> getOrdersByCustomerId(
            @Param("customerId") Long customerId,
            Pageable pageable
    );
}
