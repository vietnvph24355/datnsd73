package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.LoaiGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiGiayRepository  extends JpaRepository<LoaiGiay, Integer> {
}
