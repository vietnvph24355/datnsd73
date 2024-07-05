package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.dto.PasswordRequest;
import com.example.fploy.datn.model.request.create.UserCreateRequest;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface UserService {

    Page<User> getAllAdmin(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus);

    Page<User> getAllStaff(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus);

    Page<User> getAllClient(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus);

    UserCreateRequest add(UserCreateRequest request);
    UserCreateRequest addClient(UserCreateRequest request);
    UserCreateRequest addStaff(UserCreateRequest request);

    UserUpdateRequest update(Integer id, UserUpdateRequest request);
    UserUpdateRequest updateClient(Integer id, UserUpdateRequest request);
    UserUpdateRequest updateStaff(Integer id, UserUpdateRequest request);

    String changePassword(PasswordRequest passwordRequest);

    void delete(Integer id);

    User findById(Integer id);

    byte[] exportExcelUserAdmin() throws IOException;
    byte[] exportExcelClient() throws IOException;
}
