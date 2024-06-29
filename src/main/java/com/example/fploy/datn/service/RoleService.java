package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.model.request.create.RoleCreateRequest;
import com.example.fploy.datn.model.request.update.RoleUpdateRequest;
import org.springframework.data.domain.Page;

public interface RoleService {

    Page<Role> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText);

    RoleCreateRequest add(RoleCreateRequest request);

    RoleUpdateRequest update(Integer id, RoleUpdateRequest request);

    void delete(Integer id);


}
