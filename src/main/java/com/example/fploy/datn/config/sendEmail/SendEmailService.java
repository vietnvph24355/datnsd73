package com.example.fploy.datn.config.sendEmail;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SendEmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    String fromEmail;

    public void sendMail(User user){
        // Code gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("beesport.fpoly@gmail.com");
        String subject = "[BEE SPORT] Chào mừng bạn đến với bee sport, đây là thông tin tài khoản của bạn";
        String content = " Họ Và Tên: " + user.getName()+ "\n Số Điện Thoại: " + user.getPhone()+ "\n Email: " + user.getGmail()+ "\n Mật khẩu: " + user.getPassword();
        message.setTo(user.getGmail());
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    public String randomPasswords() {
        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(kyTu.length());
            password.append(kyTu.charAt(index));
        }
        return password.toString();
    }

}
