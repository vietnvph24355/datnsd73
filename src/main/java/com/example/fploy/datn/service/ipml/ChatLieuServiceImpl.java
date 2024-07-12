package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.ChatLieu;
import com.example.fploy.datn.repository.ChatLieuRepository;
import com.example.fploy.datn.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChatLieuServiceImpl  implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    public List<ChatLieu> getAll(){
        return chatLieuRepository.findAll();
    }

    public ChatLieu add(ChatLieu cl){
        return chatLieuRepository.save(cl);
    }

    public ChatLieu update(ChatLieu cl, Integer id) {
        Optional<ChatLieu> optional = chatLieuRepository.findById(id);
        return optional.map(o ->{
            o.setName(cl.getName());
            return chatLieuRepository.save(o);
        }).orElse(null);
    }

    public ChatLieu delete(Integer id) {
        Optional<ChatLieu> optional = chatLieuRepository.findById(id);
        return optional.map(o ->{
            chatLieuRepository.delete(o);
            return chatLieuRepository.save(o);
        }).orElse(null);
    }


}
