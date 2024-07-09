package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.User;
import com.example.fploy.datn.model.QuenMatKhauRequest.QuenMatKhauRequest;

public interface QuenMatKhauService {

    void sendEmail(User user);

    User oldPassword(QuenMatKhauRequest request);
}
