package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.SocialAccount;
import com.example.fploy.datn.entity.Token;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.request.create.SocialAccountCreateRequest;
import com.example.fploy.datn.model.request.create.TokenCreateRequest;
import com.example.fploy.datn.model.request.update.SocialAccountUpdateRequest;
import com.example.fploy.datn.model.request.update.TokenUpdateRequest;
import com.example.fploy.datn.repository.SocialAccountRepository;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.service.SocialAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialAccountServiceImpl implements SocialAccountService {

    @Autowired
    private SocialAccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<SocialAccount> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<SocialAccount> accounts = repository.getAll(pageable ,searchText);
        return accounts;
    }

    @Override
    public SocialAccountCreateRequest add(SocialAccountCreateRequest request) {
        // Chuyen doi TokenCreate thanh entity Token
        SocialAccount account = modelMapper.map(request, SocialAccount.class);

        //Lay or tao moi User tu ten dc cung cap
        User user;
        if(request.getName() != null){
            user = userRepository.findByName(request.getUserName())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            account.setUsers(user);
        }else {
            throw new IllegalArgumentException("Role name must be provided");
        }


        SocialAccount socialAccount = repository.save(account);
        return modelMapper.map(socialAccount, SocialAccountCreateRequest.class);
    }

    @Override
    public SocialAccountUpdateRequest update(Integer id, SocialAccountUpdateRequest request) {

        Optional<SocialAccount> optional = repository.findById(id);
        if (optional.isPresent()){
            SocialAccount account = optional.get();

            //Anh xa truong thong tin tu TokenRequestUpdate sang Token
            modelMapper.map(request, account);

            // Ánh xạ roleId từ UserDTO sang Role và lưu vào User
            Optional<User> user;
            if(request.getUserName() != null) {
                user = userRepository.findByName(request.getUserName());
                user.ifPresent(account::setUsers);
            }

            repository.save(account);

            return modelMapper.map(account, SocialAccountUpdateRequest.class);

        }else {
            throw new EntityNotFoundException("Token not found with id: " + id);

        }
    }

    @Override
    public void delete(Integer id) {

    }
}
