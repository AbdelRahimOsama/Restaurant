package com.spring.resturant.service.impl;

import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.mapper.ChefMapper;
import com.spring.resturant.mapper.ContactInfoMapper;
import com.spring.resturant.model.ContactInfo;
import com.spring.resturant.model.Customer;
import com.spring.resturant.repo.ContactInfoRepo;
import com.spring.resturant.repo.CustomerRepo;
import com.spring.resturant.service.ContactInfoService;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    final private ContactInfoRepo contactInfoRepo;
    final private ContactInfoMapper contactInfoMapper;
    final private CustomerRepo customerRepo;
    public ContactInfoServiceImpl(CustomerRepo customerRepo, ContactInfoMapper contactInfoMapper, ContactInfoRepo contactInfoRepo) {
        this.contactInfoRepo = contactInfoRepo;
        this.contactInfoMapper = contactInfoMapper;
        this.customerRepo = customerRepo;
    }

    @Override
    public void saveContactInfo(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = contactInfoMapper.contactInfoDtoToContactInfo(contactInfoDto);
        Long id = contactInfoDto.getCustomerDto().getId();
        Customer customer = customerRepo.findById(id).get();
        contactInfo.setCustomer(customer);
        contactInfoRepo.save(contactInfo);
    }
}
