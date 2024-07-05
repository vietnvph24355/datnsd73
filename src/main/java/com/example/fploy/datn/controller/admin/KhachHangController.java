package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/khach-hang")
public class KhachHangController {
    @Autowired
    private UserService service;

    @GetMapping()
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

    @GetMapping("/edit/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserUpdateRequest request) {
        UserUpdateRequest taiKhoan = service.updateClient(id, request);
        return ResponseEntity.ok(taiKhoan);
    }
    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        byte[] excelFile = service.exportExcelClient();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=khach_hang.xlsx");
        response.getOutputStream().write(excelFile);
    }
}
