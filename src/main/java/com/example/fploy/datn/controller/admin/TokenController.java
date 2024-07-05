package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.create.TokenCreateRequest;
import com.example.fploy.datn.model.request.update.TokenUpdateRequest;
import com.example.fploy.datn.repository.TokenRepository;
import com.example.fploy.datn.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin/api")
public class TokenController {
    @Autowired
    private TokenService service;

    @Autowired
    TokenRepository repository;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "sortField", required = false) String sortField,
                                    @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                    @RequestParam(name = "searchText", defaultValue = "") String searchText
    ){
        return ResponseEntity.ok(service.getAll(page ,pageSize,sortField,sortOrder,searchText).getContent());
    }

//    @GetMapping("/")
//    public ResponseEntity<?> getALLL(){
//        return ResponseEntity.ok(repository.findAll().);
//    }



    @PostMapping("/create")
    public ResponseEntity<?> createRole(@Valid @RequestBody TokenCreateRequest create) {
        TokenCreateRequest requestCreate = service.createToken(create);
        return new  ResponseEntity<>(requestCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id")Integer id,
                                        @RequestBody TokenUpdateRequest update) {
        TokenUpdateRequest updatedToken = service.updateToken(id, update);
        return ResponseEntity.ok(updatedToken + "Thanh cong");
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Integer id) {
        service.thuHoi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
