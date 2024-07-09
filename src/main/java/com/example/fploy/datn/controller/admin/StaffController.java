package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.create.UserCreateRequest;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RequestMapping("/admin/api/user")
@Controller
public class StaffController {

    @Autowired
    private UserService service;

    @GetMapping("/staff")
    public ResponseEntity<?> getAllStaff(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(name = "sortField", required = false) String sortField,
                                         @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
                                         @RequestParam(name = "searchText", defaultValue = "") String searchText,
                                         @RequestParam(name = "status", defaultValue = "")Boolean status,
                                         @RequestParam(name = "gender", defaultValue = "")Boolean gender
    ){
        return ResponseEntity.ok(service.getAllStaff(page,pageSize,sortField,sortOrder,searchText,gender,status).getContent());
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserCreateRequest request) {
        return new ResponseEntity<>(service.addStaff(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserUpdateRequest request) {
        UserUpdateRequest staff = service.updateStaff(id, request);
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        byte[] excelFile = service.exportExcelUserStaff();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=nhan_vien.xlsx");
        response.getOutputStream().write(excelFile);
    }
}
