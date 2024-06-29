package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.model.request.create.RoleCreateRequest;
import com.example.fploy.datn.model.request.update.RoleUpdateRequest;
import com.example.fploy.datn.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/admin/api/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "sortField", required = false) String sortField,
                                    @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                    @RequestParam(name = "searchText", defaultValue = "")String searchText){

        return ResponseEntity.ok(roleService.getAll(page,pageSize,sortField,sortOrder,searchText).getContent());

    }

    @PostMapping("/create-role")
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleCreateRequest role, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result.hasErrors());
            return ResponseEntity.ok("That Bai");
        }else {
            RoleCreateRequest create = roleService.add(role);
            return new ResponseEntity<>(create + "Thanh Cong", HttpStatus.CREATED);
        }
    }

    @PutMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestBody @Valid RoleUpdateRequest request, BindingResult result,
                                        @PathVariable(name = "id")Integer id){
        if(result.hasErrors()){
            System.out.println(result.hasErrors());
            return ResponseEntity.ok("That Bai");
        }else {
            RoleUpdateRequest updatedRole = roleService.update(id, request);
            return new ResponseEntity<>(updatedRole + "Thanh Cong", HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "id") Integer id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
