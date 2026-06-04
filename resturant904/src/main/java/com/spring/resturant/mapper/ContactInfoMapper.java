package com.spring.resturant.mapper;

import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.model.ContactInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfo contactInfoDtoToContactInfo(ContactInfoDto contactInfoDto);

    ContactInfoDto contactInfoToContactInfoDto(ContactInfo contactInfo);
}
