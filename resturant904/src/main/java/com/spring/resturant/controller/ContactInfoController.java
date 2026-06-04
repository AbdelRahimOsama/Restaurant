package com.spring.resturant.controller;

import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.service.ContactInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/Contact")
@CrossOrigin("http://localhost:4200")
public class ContactInfoController {

    final private ContactInfoService contactInfoService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveContactInfo(@RequestBody @Valid ContactInfoDto contactInfoDto){
        contactInfoService.saveContactInfo(contactInfoDto);
        return  ResponseEntity.ok().build();
    }

}
