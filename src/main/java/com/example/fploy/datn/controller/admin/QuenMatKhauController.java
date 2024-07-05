package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.QuenMatKhauRequest.QuenMatKhauRequest;
import com.example.fploy.datn.service.QuenMatKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class QuenMatKhauController {
    @Autowired
    private QuenMatKhauService quenMatKhauService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> fotgotPassword(@RequestBody QuenMatKhauRequest request){
        User taiKhoan = quenMatKhauService.oldPassword(request);

        if (taiKhoan != null) {
            quenMatKhauService.sendEmail(taiKhoan);
            System.out.println(taiKhoan.getGmail());
            return ResponseEntity.ok("Email sent successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy tài khoản với email đã cung cấp.");

        }
    }
}
