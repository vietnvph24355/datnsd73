package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.MauSac;
import com.example.fploy.datn.entity.NSX;

import java.util.List;

public interface NSXService {

    List<NSX> getAll();
    NSX add(NSX nsx);
    NSX update(NSX nsx, Integer id);
    NSX delete(Integer id);
}
