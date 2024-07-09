package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository  extends JpaRepository<MauSac, Integer> {
}
