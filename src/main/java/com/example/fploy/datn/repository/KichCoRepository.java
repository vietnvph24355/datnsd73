package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KichCoRepository  extends JpaRepository<KichCo, Integer> {
}
