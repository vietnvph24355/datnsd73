package com.example.fploy.datn.service;


import com.example.fploy.datn.entity.DeGiay;

import java.util.List;

public interface DeGiayService {

    List<DeGiay> getAll();
    DeGiay add(DeGiay dg);
    DeGiay update(DeGiay dg, Integer id);
    DeGiay delete(Integer id);
}
