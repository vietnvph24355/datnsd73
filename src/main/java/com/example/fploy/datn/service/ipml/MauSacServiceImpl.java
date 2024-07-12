package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.LoaiGiay;
import com.example.fploy.datn.entity.MauSac;
import com.example.fploy.datn.repository.LoaiGiayRepository;
import com.example.fploy.datn.repository.MauSacRepository;
import com.example.fploy.datn.service.MauSacSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl  implements MauSacSevice {

    @Autowired
    private MauSacRepository mauSacRepository;

    public List<MauSac> getAll(){
        return mauSacRepository.findAll();
    }

    public MauSac add(MauSac ms){
        return mauSacRepository.save(ms);
    }

    public MauSac update(MauSac ms, Integer id) {
        Optional<MauSac> optional = mauSacRepository.findById(id);
        return optional.map(o ->{
            o.setName(ms.getName());
            return mauSacRepository.save(o);
        }).orElse(null);
    }

    public MauSac delete(Integer id) {
        Optional<MauSac> optional = mauSacRepository.findById(id);
        return optional.map(o ->{
            mauSacRepository.delete(o);
            return mauSacRepository.save(o);
        }).orElse(null);
    }
}
