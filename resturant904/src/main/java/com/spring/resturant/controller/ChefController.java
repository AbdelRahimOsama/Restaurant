package com.spring.resturant.controller;

import com.spring.resturant.dto.ChefDto;

import com.spring.resturant.service.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chef")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/team")
    public ResponseEntity<List<ChefDto>> getChefs() {
        return ResponseEntity.ok(chefService.getChefs());
    }
}
