package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.model.request.create.RoleCreateRequest;
import com.example.fploy.datn.model.request.update.RoleUpdateRequest;
import com.example.fploy.datn.repository.RoleRepository;
import com.example.fploy.datn.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Role> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Role> role = roleRepository.findByAll(pageable,searchText);
        return role;
    }

    @Transactional
    @Override
    public RoleCreateRequest add(RoleCreateRequest request) {

        // Anh xa roleCreateRequest qua Entity Role;
       Role role = mapper.map(request, Role.class);


       // Luu
       Role saveRole = roleRepository.save(role);

       return mapper.map(saveRole, RoleCreateRequest.class);
    }

    @Transactional
    @Override
    public RoleUpdateRequest update(Integer id, RoleUpdateRequest request) {
        // Lay id Role
       Optional<Role> optional = roleRepository.findById(id);

       if(optional.isPresent()){

           Role role = optional.get();

           // Ánh xạ các trường thông tin từ RoleDTO sang Role
           mapper.map(request, role);

           // Lưu lại vào cơ sở dữ liệu
           roleRepository.save(role);

           // Trả về roleDTO đã được cập nhật
            return mapper.map(role, RoleUpdateRequest.class);
       }else {
           throw new EntityNotFoundException("Role not found with id: " + id);
       }
    }

    @Override
    public void delete(Integer id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            roleRepository.findById(id);
        } else {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
    }
}
