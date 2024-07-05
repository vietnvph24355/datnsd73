package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.Token;
import com.example.fploy.datn.model.request.create.TokenCreateRequest;
import com.example.fploy.datn.model.request.update.TokenUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TokenService {

    Page<Token> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText);
    //Page<TokenResponse> getAll1(Integer page, Integer pageSize, String sortField, String sortOrder, String tokens, String status);

    TokenCreateRequest createToken(TokenCreateRequest create);

    TokenUpdateRequest updateToken(Integer id, TokenUpdateRequest update);

    void hetHan();

    void thuHoi(Integer id);
}
