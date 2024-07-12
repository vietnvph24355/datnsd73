package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.KichCo;
import com.example.fploy.datn.entity.LoaiGiay;
import com.example.fploy.datn.repository.KichCoRepository;
import com.example.fploy.datn.repository.LoaiGiayRepository;
import com.example.fploy.datn.service.LoaiGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiGiayServiceImpl  implements LoaiGiayService {
    @Autowired
    private LoaiGiayRepository loaiGiayRepository;

    public List<LoaiGiay> getAll(){
        return loaiGiayRepository.findAll();
    }

    public LoaiGiay add(LoaiGiay lg){
        return loaiGiayRepository.save(lg);
    }

    public LoaiGiay update(LoaiGiay lg, Integer id) {
        Optional<LoaiGiay> optional = loaiGiayRepository.findById(id);
        return optional.map(o ->{
            o.setName(lg.getName());
            return loaiGiayRepository.save(o);
        }).orElse(null);
    }

    public LoaiGiay delete(Integer id) {
        Optional<LoaiGiay> optional = loaiGiayRepository.findById(id);
        return optional.map(o ->{
            loaiGiayRepository.delete(o);
            return loaiGiayRepository.save(o);
        }).orElse(null);
    }
}
