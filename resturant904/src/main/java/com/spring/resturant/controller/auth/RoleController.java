package com.spring.resturant.controller.auth;


import com.spring.resturant.dto.Exception.IdMustNull;
import com.spring.resturant.dto.RoleDto;
import com.spring.resturant.service.RoleService;
import jakarta.transaction.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Role")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping("/update_role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> add_role(@RequestBody RoleDto roleDto) throws IdMustNull {
          roleService.add_role(roleDto);
          return ResponseEntity.ok().build();
    }

}
