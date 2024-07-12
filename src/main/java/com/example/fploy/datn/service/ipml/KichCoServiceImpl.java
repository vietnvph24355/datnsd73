package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.DeGiay;
import com.example.fploy.datn.entity.KichCo;
import com.example.fploy.datn.repository.DeGiayRepository;
import com.example.fploy.datn.repository.KichCoRepository;
import com.example.fploy.datn.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KichCoServiceImpl  implements KichCoService {

    @Autowired
    private KichCoRepository kichCoRepository;

    public List<KichCo> getAll(){
        return kichCoRepository.findAll();
    }

    public KichCo add(KichCo kc){
        return kichCoRepository.save(kc);
    }

    public KichCo update(KichCo kc, Integer id) {
        Optional<KichCo> optional = kichCoRepository.findById(id);
        return optional.map(o ->{
            o.setName(kc.getName());
            return kichCoRepository.save(o);
        }).orElse(null);
    }

    public KichCo delete(Integer id) {
        Optional<KichCo> optional = kichCoRepository.findById(id);
        return optional.map(o ->{
            kichCoRepository.delete(o);
            return kichCoRepository.save(o);
        }).orElse(null);
    }
}
