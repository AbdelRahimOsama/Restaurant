package com.spring.resturant.repo;

import com.spring.resturant.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo,Long> {

}
