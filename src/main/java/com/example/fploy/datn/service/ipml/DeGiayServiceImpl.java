package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.ChatLieu;
import com.example.fploy.datn.entity.DeGiay;
import com.example.fploy.datn.repository.ChatLieuRepository;
import com.example.fploy.datn.repository.DeGiayRepository;
import com.example.fploy.datn.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeGiayServiceImpl  implements DeGiayService {
    @Autowired
    private DeGiayRepository deGiayRepository;

    public List<DeGiay> getAll(){
        return deGiayRepository.findAll();
    }

    public DeGiay add(DeGiay dg){
        return deGiayRepository.save(dg);
    }

    public DeGiay update(DeGiay dg, Integer id) {
        Optional<DeGiay> optional = deGiayRepository.findById(id);
        return optional.map(o ->{
            o.setName(dg.getName());
            return deGiayRepository.save(o);
        }).orElse(null);
    }

    public DeGiay delete(Integer id) {
        Optional<DeGiay> optional = deGiayRepository.findById(id);
        return optional.map(o ->{
            deGiayRepository.delete(o);
            return deGiayRepository.save(o);
        }).orElse(null);
    }

}
