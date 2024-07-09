package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.SocialAccount;
import com.example.fploy.datn.model.request.create.SocialAccountCreateRequest;
import com.example.fploy.datn.model.request.update.SocialAccountUpdateRequest;
import org.springframework.data.domain.Page;

public interface SocialAccountService {
    Page<SocialAccount> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText);

    SocialAccountCreateRequest add(SocialAccountCreateRequest request);

    SocialAccountUpdateRequest update(Integer id, SocialAccountUpdateRequest request);

    void delete(Integer id);

}
