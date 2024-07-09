package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeGiayRepository  extends JpaRepository<DeGiay, Integer> {
}
