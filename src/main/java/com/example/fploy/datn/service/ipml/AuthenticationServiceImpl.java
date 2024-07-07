package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.config.sendEmail.SendEmailService;
import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.exception.BadRequestException;
import com.example.fploy.datn.model.dto.JwtAuthenticationResponse;
import com.example.fploy.datn.model.dto.RefreshTokenRequest;
import com.example.fploy.datn.model.dto.SigninRequest;
import com.example.fploy.datn.model.dto.SingUpRequest;
import com.example.fploy.datn.repository.RoleRepository;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.security.UserInforDetailService;
import com.example.fploy.datn.service.AuthenticationService;
import com.example.fploy.datn.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserInforDetailService service;

    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private SendEmailService sendEmailService;

    public AuthenticationServiceImpl(UserInforDetailService service, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User signup(SingUpRequest signUpRequest) {
        User gmail = userRepository.findGmail1(signUpRequest.getGmail());
        if (gmail != null) {
            throw new BadRequestException("Email đã tồn tại trong hệ thống!");
        }

        if(signUpRequest.getGender()==null){
            signUpRequest.setGender(Boolean.valueOf("3"));
        }

        User user = new User();

        user.setName(signUpRequest.getName());
        user.setGmail(signUpRequest.getGmail());
        user.setRole(roleRepository.findId(Integer.valueOf(2)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        if(user.getGender() == null){
            user.setGender(Boolean.valueOf("OTHER"));
        }
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        User user1 = userRepository.save(user);
        user1.setPassword(signUpRequest.getPassword());
        sendEmailService.sendMail(user1);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        userRepository.save(user1);
        return user1;
    }

    @Override
    public boolean addAdmin() {
        Optional<Role> roleId = roleRepository.findById(Integer.valueOf(1));
        Role role = roleId.get();
        User user = new User();
        user.setName("nguyenviet");
        user.setPhone("0867291082");
        user.setGmail("viet01232003@gmail.com");
        user.setRole(role);
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        if(user.getGender()==null){
            user.setGender(Boolean.valueOf("OTHER"));
        }
        user.setPassword(new BCryptPasswordEncoder().encode("Aa123456@"));
        userRepository.save(user);
        return true;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getGmail(),
                    signinRequest.getPassword()));
        } catch (AuthenticationException e) {
            // Invalid credentials
            throw new BadRequestException("Tài khoản hoặc mật khẩu không tồn tại.");
        }

        User taiKhoan = userRepository.findGmail1(signinRequest.getGmail());
//        GioHang gioHang = gioHangRepository.getOne(taiKhoan.getId());
        var userToke = service.loadUserByUsername(signinRequest.getGmail());
        var jwt = jwtService.generateToken(userToke);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userToke);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setTokens(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRoleId(taiKhoan.getRole().getId());
        jwtAuthenticationResponse.setAcountId(taiKhoan.getId());
        jwtAuthenticationResponse.setGmail(taiKhoan.getPhone());
        jwtAuthenticationResponse.setName(taiKhoan.getName());
//        jwtAuthenticationResponse.setIdGioHang(gioHang.getId());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getTokens());
        UserDetails userToken = service.loadUserByUsername(userEmail);

        if(jwtService.isTokenValid(refreshTokenRequest.getTokens(),userToken)){
            var jwt = jwtService.generateToken(userToken);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setTokens(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getTokens());

            return jwtAuthenticationResponse;
        }


        return null;
    }
}
