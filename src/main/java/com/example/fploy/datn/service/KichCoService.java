package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.DeGiay;
import com.example.fploy.datn.entity.KichCo;

import java.util.List;

public interface KichCoService {

    List<KichCo> getAll();
    KichCo add(KichCo kc);
    KichCo update(KichCo kc, Integer id);
    KichCo delete(Integer id);
}
