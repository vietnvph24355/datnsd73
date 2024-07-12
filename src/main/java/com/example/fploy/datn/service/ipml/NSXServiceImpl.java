package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.MauSac;
import com.example.fploy.datn.entity.NSX;
import com.example.fploy.datn.repository.MauSacRepository;
import com.example.fploy.datn.repository.NSXRepository;
import com.example.fploy.datn.service.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NSXServiceImpl  implements NSXService {

    @Autowired
    private NSXRepository nsxRepository;

    public List<NSX> getAll(){
        return nsxRepository.findAll();
    }

    public NSX add(NSX nsx){
        return nsxRepository.save(nsx);
    }

    public NSX update(NSX nsx, Integer id) {
        Optional<NSX> optional = nsxRepository.findById(id);
        return optional.map(o ->{
            o.setName(nsx.getName());
            o.setStatus(nsx.getStatus());
            return nsxRepository.save(o);
        }).orElse(null);
    }

    public NSX delete(Integer id) {
        Optional<NSX> optional = nsxRepository.findById(id);
        return optional.map(o ->{
            nsxRepository.delete(o);
            return nsxRepository.save(o);
        }).orElse(null);
    }
}
