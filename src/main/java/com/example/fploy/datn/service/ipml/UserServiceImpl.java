package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.config.sendEmail.SendEmailService;
import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.dto.PasswordRequest;
import com.example.fploy.datn.model.request.create.UserCreateRequest;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.repository.RoleRepository;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public Page<User> getAllAdmin(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> users = userRepository.findByAllAdmin(pageable,searchText,searchStatus,searchGender);
        return users;
    }

    @Override
    public Page<User> getAllStaff(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> users = userRepository.findByAllStaff(pageable,searchText,searchStatus,searchGender);
        return users;
    }

    @Override
    public Page<User> getAllClient(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Boolean searchGender, Boolean searchStatus) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> users = userRepository.findByAllClient(pageable,searchText,searchStatus,searchGender);
        return users;
    }

    @Transactional
    @Override
    public UserCreateRequest add(UserCreateRequest request) {
        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        // Lấy hoặc tạo mới vai trò từ tên được cung cấp
//        Role role;
//        if (request.getRoleId() != null) {
//            role = roleRepository.findById(request.getRoleId())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(roleRepository.findId(Integer.valueOf(1)));
//        } else if (request.getRoleName() != null) {
//            role = roleRepository.findByName(request.getRoleName())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(role);
//        } else {
//            throw new IllegalArgumentException("Role id or name must be provided");
//        }
        user.setRole(roleRepository.findId(Integer.valueOf(1)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        //user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(user, UserCreateRequest.class);
    }

    @Override
    public UserCreateRequest addClient(UserCreateRequest request) {

        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        // Lấy hoặc tạo mới vai trò từ tên được cung cấp
//        Role role;
//        if (request.getRoleId() != null) {
//            role = roleRepository.findById(request.getRoleId())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(roleRepository.findId(Integer.valueOf(1)));
//        } else if (request.getRoleName() != null) {
//            role = roleRepository.findByName(request.getRoleName())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(role);
//        } else {
//            throw new IllegalArgumentException("Role id or name must be provided");
//        }
        user.setRole(roleRepository.findId(Integer.valueOf(2)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        //user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(user, UserCreateRequest.class);
    }

    @Override
    public UserCreateRequest addStaff(UserCreateRequest request) {

        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        // Lấy hoặc tạo mới vai trò từ tên được cung cấp
//        Role role;
//        if (request.getRoleId() != null) {
//            role = roleRepository.findById(request.getRoleId())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(roleRepository.findId(Integer.valueOf(1)));
//        } else if (request.getRoleName() != null) {
//            role = roleRepository.findByName(request.getRoleName())
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            user.setRole(role);
//        } else {
//            throw new IllegalArgumentException("Role id or name must be provided");
//        }
        user.setRole(roleRepository.findId(Integer.valueOf(3)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        //user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(user, UserCreateRequest.class);
    }

    @Override
    public UserUpdateRequest update(Integer id, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Ánh xạ các trường thông tin từ UserDTO sang User
            mapper.map(request, user);

            // Ánh xạ roleId từ UserDTO sang Role và lưu vào User
//            Optional<Role> optionalRole;
//            if (update.getRoleId() != null) {
//                optionalRole = roleRepository.findById(update.getRoleId());
//                optionalRole.ifPresent(user::setRole);
//            }else if(update.getRoleName() != null) {
//               optionalRole = roleRepository.findByName(update.getRoleName());
//               optionalRole.ifPresent(user::setRole);
//            }
            Role role;
            if (request.getRoleId() != null) {
                role = roleRepository.findById(request.getRoleId())
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                user.setRole(role);
            } else if (request.getRoleName() != null) {
                role = roleRepository.findByName(request.getRoleName())
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                user.setRole(role);
            } else {
                throw new IllegalArgumentException("Role id or name must be provided");
            }

            // Update user
            userRepository.save(user);

            // Trả về userDTO đã được cập nhật
            return mapper.map(user, UserUpdateRequest.class);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public UserUpdateRequest updateClient(Integer id, UserUpdateRequest request) {
        return null;
    }

    @Override
    public UserUpdateRequest updateStaff(Integer id, UserUpdateRequest request) {
        return null;
    }

    @Override
    public String changePassword(PasswordRequest passwordRequest) {
//        Optional<User> optionalTaiKhoan = userRepository.findById(passwordRequest.getId());
//        if (optionalTaiKhoan.isPresent()) {
//            User accountId = optionalTaiKhoan.get();
//            System.out.println("aaaa"+accountId);
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountId.getSoDienThoai(), passwordRequest.getMatKhauCu()));
//
//            if (authentication.isAuthenticated()) {
//                System.out.println("da vao");
//
//                if(!passwordRequest.getMatKhauMoi().equals(passwordRequest.getNhapLaiMatKhau())){
//                    return"Mật khẩu nhập lại phải trùng nhau";
//                }
//
//                accountId.setMatKhau(passwordEncoder.encode(passwordRequest.getNhapLaiMatKhau()));
//                userRepository.save(accountId);
//                return"Bạn đã đổi mật khẩu thành công";
//            }
//
//            else {
//                return"Mật khẩu cũ không trùng với mật khẩu của tài khoản";
//            }
//        } else {
//            return "Đổi mật khẩu thất bại";
//        }

        return null;
    }

    @Override
    public void delete(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Khong tim thay id" + id));

        user.setIsActivate(false);

        userRepository.save(user);

    }
}
