package com.spring.resturant.controller.auth;

import com.spring.resturant.controller.vm.TokenResponseVm;
import com.spring.resturant.dto.CustomerDto;
import com.spring.resturant.dto.Exception.Found;
import com.spring.resturant.dto.Exception.NotFound;
import com.spring.resturant.service.AuthService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/sign_up")
    public ResponseEntity<TokenResponseVm> signUp(@RequestBody @Valid CustomerDto customerDto) throws Found {
        return ResponseEntity.ok(authService.signUp(customerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseVm> login(@RequestBody CustomerDto customerDto) throws Found, NotFound,SystemException {
        return ResponseEntity.ok(authService.login(customerDto));
    }


}
