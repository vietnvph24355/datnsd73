package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.SocialAccount;
import com.example.fploy.datn.entity.User;
import org.springframework.data.domain.Page;

public interface SocialAccountService {
    Page<SocialAccount> getAllAdmin(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText);

}
