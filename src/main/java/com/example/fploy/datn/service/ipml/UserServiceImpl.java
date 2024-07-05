package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.config.sendEmail.SendEmailService;
import com.example.fploy.datn.entity.Role;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.exception.BadRequestException;
import com.example.fploy.datn.exception.NotFoundException;
import com.example.fploy.datn.model.dto.PasswordRequest;
import com.example.fploy.datn.model.request.create.UserCreateRequest;
import com.example.fploy.datn.model.request.update.UserUpdateRequest;
import com.example.fploy.datn.repository.RoleRepository;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null){
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống!");
        }
//        if (request.getGender()==null){
//            request.getGender(Boolean.valueOf("3"));
//        }

        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        user.setRole(roleRepository.findId(Integer.valueOf(1)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(userSave, UserCreateRequest.class);
    }

    @Override
    public UserCreateRequest addClient(UserCreateRequest request) {

        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null){
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống!");
        }
//        if (request.getGender()==null){
//            request.getGender(Boolean.valueOf("3"));
//        }

        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        user.setRole(roleRepository.findId(Integer.valueOf(2)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(userSave, UserCreateRequest.class);
    }

    @Override
    public UserCreateRequest addStaff(UserCreateRequest request) {


        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null){
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống!");
        }
//        if (request.getGender()==null){
//            request.getGender(Boolean.valueOf("3"));
//        }

        // Chuyển đổi UserDTO thành entity User
        User user = mapper.map(request, User.class);

        user.setRole(roleRepository.findId(Integer.valueOf(3)));
        user.setIsActivate(true);
        user.setAvatar("defaultAvatar.jpg");
        user.setPassword(sendEmailService.randomPasswords());

        User userSave = userRepository.save(user);
        sendEmailService.sendMail(userSave);
        user.setPassword(new BCryptPasswordEncoder().encode(userSave.getPassword()));
        userSave = userRepository.save(user);

        return mapper.map(userSave, UserCreateRequest.class);
    }

    @Override
    public UserUpdateRequest update(Integer id, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null && !request.getGmail().equals(gmail.getGmail())){
            if(userRepository.existsByGmail(request.getGmail()));
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống. Vui lòng sử dụng gmail khác!");
        }

        User user = optionalUser.get();

        // Ánh xạ các trường thông tin từ UserDTO sang User
        mapper.map(request, user);

        // Update user
        userRepository.save(user);

        // Trả về userDTO đã được cập nhật
        return mapper.map(user, UserUpdateRequest.class);
    }

    @Override
    public UserUpdateRequest updateClient(Integer id, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null && !request.getGmail().equals(gmail.getGmail())){
            if(userRepository.existsByGmail(request.getGmail()));
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống. Vui lòng sử dụng gmail khác!");
        }

            User user = optionalUser.get();

            // Ánh xạ các trường thông tin từ UserDTO sang User
            mapper.map(request, user);

            // Update user
            userRepository.save(user);

            // Trả về userDTO đã được cập nhật
            return mapper.map(user, UserUpdateRequest.class);

    }

    @Override
    public UserUpdateRequest updateStaff(Integer id, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        User gmail = userRepository.findGmail1(request.getGmail());

        if(gmail != null && !request.getGmail().equals(gmail.getGmail())){
            if(userRepository.existsByGmail(request.getGmail()));
            throw new BadRequestException("Gmail đã tồn tại trong hệ thống. Vui lòng sử dụng gmail khác!");
        }

        User user = optionalUser.get();

        // Ánh xạ các trường thông tin từ UserDTO sang User
        mapper.map(request, user);

        // Update user
        userRepository.save(user);

        // Trả về userDTO đã được cập nhật
        return mapper.map(user, UserUpdateRequest.class);
    }

    @Override
    public String changePassword(PasswordRequest passwordRequest) {
        Optional<User> optionalTaiKhoan = userRepository.findById(passwordRequest.getId());
        if (optionalTaiKhoan.isPresent()) {
            User accountId = optionalTaiKhoan.get();
            System.out.println("aaaa"+accountId);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountId.getGmail(), passwordRequest.getMatKhauCu()));

            if (authentication.isAuthenticated()) {
                System.out.println("da vao");

                if(!passwordRequest.getMatKhauMoi().equals(passwordRequest.getNhapLaiMatKhau())){
                    return"Mật khẩu nhập lại phải trùng nhau";
                }

                accountId.setPassword(passwordEncoder.encode(passwordRequest.getNhapLaiMatKhau()));
                userRepository.save(accountId);
                return"Bạn đã đổi mật khẩu thành công";
            }

            else {
                return"Mật khẩu cũ không trùng với mật khẩu của tài khoản";
            }
        } else {
            return "Đổi mật khẩu thất bại";
        }

    }

    @Override
    public void delete(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Khong tim thay id" + id));

        user.setIsActivate(false);

        userRepository.save(user);

    }

    @Override
    public User findById(Integer id) {
       Optional<User> user = userRepository.findById(id);
       if (user.isEmpty()){
           throw new NotFoundException("Tai khoan da ton tai");
       }
       return mapper.map(user.get(), User.class);
    }

    @Override
    public byte[] exportExcelUserStaff() throws IOException {

        List<User> userList = userRepository.findAllNhanVienExcel();
        // Implement this method in your service

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("TaiKhoan");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Ho va Ten");
        headerRow.createCell(2).setCellValue("Ngay sinh");
        headerRow.createCell(3).setCellValue("Gioi tinh");
        headerRow.createCell(4).setCellValue("So dien thoai");
        headerRow.createCell(5).setCellValue("Email");
        headerRow.createCell(6).setCellValue("facebook");
        headerRow.createCell(7).setCellValue("google");
        headerRow.createCell(8).setCellValue("Dia chi cu the");
        headerRow.createCell(9).setCellValue("Anh dai dien");
        headerRow.createCell(10).setCellValue("Mat khau");
        headerRow.createCell(11).setCellValue("Ngay tao");
        headerRow.createCell(12).setCellValue("Ngay sua");
        headerRow.createCell(13).setCellValue("Trang thai");
        headerRow.createCell(14).setCellValue("Vai tro ID");

        // Create data rows
        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getDateOfBirth());
            row.createCell(3).setCellValue(user.getGender());
            if (user.getGender() != null) {
                row.createCell(3).setCellValue(user.getGender());
            } else {
                row.createCell(3).setCellValue((String) null);
            }
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getGmail());
            row.createCell(6).setCellValue(user.getFacebook());
            row.createCell(7).setCellValue(user.getGoogle());
            row.createCell(8).setCellValue(user.getDiaChi());
            row.createCell(9).setCellValue(user.getAvatar());
            row.createCell(10).setCellValue(user.getPassword());
            row.createCell(11).setCellValue(user.getCreateAt());
            row.createCell(12).setCellValue(user.getUpdateAt());
            if (user.getIsActivate() != null) {
                row.createCell(13).setCellValue(user.getIsActivate());
            } else {
                row.createCell(13).setCellValue((String) null);
            }

            row.createCell(14).setCellValue(user.getRole().getName());
        }

        // Set the response headers
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    @Override
    public byte[] exportExcelClient() throws IOException{
        List<User> userList = userRepository.findAllClientExcel(); // Implement this method in your service

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("TaiKhoan");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Ho va Ten");
        headerRow.createCell(2).setCellValue("Ngay sinh");
        headerRow.createCell(3).setCellValue("Gioi tinh");
        headerRow.createCell(4).setCellValue("So dien thoai");
        headerRow.createCell(5).setCellValue("Email");
        headerRow.createCell(6).setCellValue("facebook");
        headerRow.createCell(7).setCellValue("google");
        headerRow.createCell(8).setCellValue("Dia chi cu the");
        headerRow.createCell(9).setCellValue("Anh dai dien");
        headerRow.createCell(10).setCellValue("Mat khau");
        headerRow.createCell(11).setCellValue("Ngay tao");
        headerRow.createCell(12).setCellValue("Ngay sua");
        headerRow.createCell(13).setCellValue("Trang thai");
        headerRow.createCell(14).setCellValue("Vai tro ID");

        // Create data rows
        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getDateOfBirth());
            row.createCell(3).setCellValue(user.getGender());
            if (user.getGender() != null) {
                row.createCell(3).setCellValue(user.getGender());
            } else {
                row.createCell(3).setCellValue((String) null);
            }
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getGmail());
            row.createCell(6).setCellValue(user.getFacebook());
            row.createCell(7).setCellValue(user.getGoogle());
            row.createCell(8).setCellValue(user.getDiaChi());
            row.createCell(9).setCellValue(user.getAvatar());
            row.createCell(10).setCellValue(user.getPassword());
            row.createCell(11).setCellValue(user.getCreateAt());
            row.createCell(12).setCellValue(user.getUpdateAt());
            if (user.getIsActivate() != null) {
                row.createCell(13).setCellValue(user.getIsActivate());
            } else {
                row.createCell(13).setCellValue((String) null);
            }

            row.createCell(14).setCellValue(user.getRole().getName());
        }

        // Set the response headers
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}
