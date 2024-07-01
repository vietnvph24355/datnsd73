package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.dto.JwtAuthenticationResponse;
import com.example.fploy.datn.model.dto.RefreshTokenRequest;
import com.example.fploy.datn.model.dto.SigninRequest;
import com.example.fploy.datn.model.dto.SingUpRequest;

public interface AuthenticationService {

    User signup(SingUpRequest signUpRequest);

    boolean addAdmin();

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
