package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository  extends JpaRepository<ChatLieu, Integer> {
}
