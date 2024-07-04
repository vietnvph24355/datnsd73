package com.example.fploy.datn.security;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInforDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserInforDetail userInforDetail;

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findGmail(gmail);
        return optional.map(UserInforDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("gmail not found"));
    }
}
