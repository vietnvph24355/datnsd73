package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.dto.*;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.service.AuthenticationService;
import com.example.fploy.datn.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    private UserService service;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Dang nhap
    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody SingUpRequest request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> add(){
        return ResponseEntity.ok(authenticationService.addAdmin());
    }

    //Dang ky
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SigninRequest request){
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    //Dang xuat
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(request.getServerName());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    @PostMapping("/doi-mat-khau")
    public ResponseEntity<?> doiMatKhau(@RequestBody PasswordRequest passwordRequest){
        return ResponseEntity.ok(service.changePassword(passwordRequest));
    }

    @GetMapping("/editTT/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/updateTT/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserUpdateRequest request) {
        UserUpdateRequest updateClient = service.updateClient(id, request);
        return ResponseEntity.ok(updateClient);
    }
}

