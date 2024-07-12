package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.ChatLieu;

import java.util.List;

public interface ChatLieuService {

    List<ChatLieu> getAll();
    ChatLieu add(ChatLieu cl);
    ChatLieu update(ChatLieu cl, Integer id);
    ChatLieu delete(Integer id);

}
