package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.create.UserCreateRequest;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/admin/api/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/client")
    public ResponseEntity<?> getAllClient(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "sortField", required = false) String sortField,
                                          @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                          @RequestParam(name = "searchText", defaultValue = "") String searchText,
                                          @RequestParam(name = "status", defaultValue = "")Boolean status,
                                          @RequestParam(name = "gender", defaultValue = "")Boolean gender
    ){
        return ResponseEntity.ok(service.getAllClient(page,pageSize,sortField,sortOrder,searchText,gender,status).getContent());
    }
    @GetMapping("/admin")
    public ResponseEntity<?> getAllAdmin( @RequestParam(name = "page", defaultValue = "1") Integer page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "sortField", required = false) String sortField,
                                          @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                          @RequestParam(name = "searchText", defaultValue = "") String searchText,
                                          @RequestParam(name = "status", defaultValue = "")Boolean status,
                                          @RequestParam(name = "gender", defaultValue = "")Boolean gender
    ){
        return ResponseEntity.ok(service.getAllAdmin(page,pageSize,sortField,sortOrder,searchText,gender,status).getContent());
    }

    @GetMapping("/staff")
    public ResponseEntity<?> getAllStaff( @RequestParam(name = "page", defaultValue = "1") Integer page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "sortField", required = false) String sortField,
                                          @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                          @RequestParam(name = "searchText", defaultValue = "") String searchText,
                                          @RequestParam(name = "status", defaultValue = "")Boolean status,
                                          @RequestParam(name = "gender", defaultValue = "")Boolean gender
    ){
        return ResponseEntity.ok(service.getAllStaff(page,pageSize,sortField,sortOrder,searchText,gender,status).getContent());
    }



    @PostMapping("/create-admin")
    public ResponseEntity<?> createRole(@Valid @RequestBody UserCreateRequest create, BindingResult result) {
        if(result.hasErrors()){
            System.out.println(result.hasErrors());
            return ResponseEntity.ok("That Bai");
        }else {

            UserCreateRequest requestCreate = service.add(create);
            return new ResponseEntity<>(requestCreate + "Thanh Cong", HttpStatus.CREATED);
        }
    }

    @PutMapping("/update-admin/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id")Integer id,
                                        @RequestBody UserUpdateRequest update,
                                        BindingResult result) {
        if(result.hasErrors()){
            System.out.println(result.hasErrors());
            return ResponseEntity.ok("That Bai");
        }else {
            UserUpdateRequest updatedUser = service.update(id, update);
            return ResponseEntity.ok(updatedUser);
        }
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
