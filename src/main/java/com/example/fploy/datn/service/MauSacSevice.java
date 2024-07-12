package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.LoaiGiay;
import com.example.fploy.datn.entity.MauSac;

import java.util.List;

public interface MauSacSevice {

    List<MauSac> getAll();
    MauSac add(MauSac ms);
    MauSac update(MauSac ms, Integer id);
    MauSac delete(Integer id);
}
