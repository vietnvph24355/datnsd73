package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.config.sendEmail.SendEmailService;
import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.QuenMatKhauRequest.QuenMatKhauRequest;
import com.example.fploy.datn.repository.UserRepository;
import com.example.fploy.datn.service.QuenMatKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class QuenMatKhauServiceImpl implements QuenMatKhauService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  JavaMailSender javaMailSender;

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public void sendEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("beesport.sd73@gmail.com");
        String subject = "[BEE SPORT] Chào mừng bạn đến với bee sport, đây là mật khẩu của bạn";
        String content = "Mật khẩu: " + sendEmailService.randomPasswords();
        content = content.replace("Mật khẩu: ", "");
        message.setTo(user.getGmail());
        message.setSubject(subject);
        message.setText("Mật khẩu: " + content);
        user.setPassword(new BCryptPasswordEncoder().encode(content));
        userRepository.save(user);

        javaMailSender.send(message);
    }

    @Override
    public User oldPassword(QuenMatKhauRequest request) {
        return userRepository.findUserByGmail(request.getEmail());
    }
}
