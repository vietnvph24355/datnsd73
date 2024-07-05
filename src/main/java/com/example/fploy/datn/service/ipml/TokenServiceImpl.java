package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.Token;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.request.create.TokenCreateRequest;
import com.example.fploy.datn.model.request.update.TokenUpdateRequest;
import com.example.fploy.datn.repository.TokenRepository;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.service.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Token> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Token> tokens = tokenRepository.getAll(pageable ,searchText);
        return tokens;
    }

    @Override
    public TokenCreateRequest createToken(TokenCreateRequest create) {
        // Chuyen doi TokenCreate thanh entity Token
        Token token = modelMapper.map(create, Token.class);

        //Lay or tao moi User tu ten dc cung cap
        User user;
        if(create.getUserName() != null){
            user = userRepository.findByName(create.getUserName())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            token.setUsers(user);
        }else {
            throw new IllegalArgumentException("Role name must be provided");
        }
        token.setRevoked(1);
        token.setExpired(2);

        Token tokenSave = tokenRepository.save(token);
        return modelMapper.map(tokenSave, TokenCreateRequest.class);
    }

    @Override
    public TokenUpdateRequest updateToken(Integer id, TokenUpdateRequest update) {
        Optional<Token> optional = tokenRepository.findById(id);
        if (optional.isPresent()){
            Token token = optional.get();

            //Anh xa truong thong tin tu TokenRequestUpdate sang Token
            modelMapper.map(update, token);

            // Ánh xạ roleId từ UserDTO sang Role và lưu vào User
            Optional<User> user;
            if(update.getUserName() != null) {
                user = userRepository.findByName(update.getUserName());
                user.ifPresent(token::setUsers);
            }

            tokenRepository.save(token);

            return modelMapper.map(token, TokenUpdateRequest.class);

        }else {
            throw new EntityNotFoundException("Token not found with id: " + id);

        }
    }

    @Override
    public void hetHan() {
//        List<Token> tokens = tokenRepository.TokenKhongHuy();
//        // Creating a Timestamp object
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        // Converting Timestamp to LocalDateTime
//        LocalDateTime now = timestamp.toLocalDateTime();
//        //LocalDateTime now = LocalDateTime.now();
//        for (Token token : tokens){
//            if(now.isAfter(token.getTimeEnd().to)){
//                token.setExpired(2);
//            }
//        }
//        tokenRepository.saveAll(tokens);
    }

    @Override
    public void thuHoi(Integer id) {
        Token token = tokenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay id" + id));
        token.setRevoked(1);
        tokenRepository.save(token);
    }
}
