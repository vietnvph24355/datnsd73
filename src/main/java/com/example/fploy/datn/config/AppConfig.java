package com.example.fploy.datn.config;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.dto.JwtAuthenticationResponse;
import com.example.fploy.datn.model.dto.RefreshTokenRequest;
import com.example.fploy.datn.model.dto.SigninRequest;
import com.example.fploy.datn.model.dto.SingUpRequest;
import com.example.fploy.datn.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean
//    public AuthenticationService authenticationService() {
//        return new AuthenticationService() {
//            @Override
//            public User signup(SingUpRequest signUpRequest) {
//                return null;
//            }
//
//            @Override
//            public boolean addAdmin() {
//                return false;
//            }
//
//            @Override
//            public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
//                return null;
//            }
//
//            @Override
//            public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//                return null;
//            }
//        };
//    }
}
