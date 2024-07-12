package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.KichCo;
import com.example.fploy.datn.entity.LoaiGiay;

import java.util.List;

public interface LoaiGiayService {
    List<LoaiGiay> getAll();
    LoaiGiay add(LoaiGiay lg);
    LoaiGiay update(LoaiGiay lg, Integer id);
    LoaiGiay delete(Integer id);
}
