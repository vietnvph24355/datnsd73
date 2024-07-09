package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.create.SocialAccountCreateRequest;
import com.example.fploy.datn.model.request.create.TokenCreateRequest;
import com.example.fploy.datn.model.request.update.SocialAccountUpdateRequest;
import com.example.fploy.datn.model.request.update.TokenUpdateRequest;
import com.example.fploy.datn.service.SocialAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/admin/api/social-account")
public class SocialAccountController {

    @Autowired
    private SocialAccountService service;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "sortField", required = false) String sortField,
                                    @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                    @RequestParam(name = "searchText", defaultValue = "") String searchText
    ){
        return ResponseEntity.ok(service.getAll(page ,pageSize,sortField,sortOrder,searchText).getContent());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@Valid @RequestBody SocialAccountCreateRequest create) {
        SocialAccountCreateRequest requestCreate = service.add(create);
        return new  ResponseEntity<>(requestCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id")Integer id,
                                        @RequestBody SocialAccountUpdateRequest update) {
        SocialAccountUpdateRequest updatedToken = service.update(id, update);
        return ResponseEntity.ok(updatedToken + "Thanh cong");
    }
}
