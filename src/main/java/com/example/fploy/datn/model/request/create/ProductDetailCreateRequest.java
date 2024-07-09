package com.example.fploy.datn.model.request.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailCreateRequest {
    private int cost;
    private int quantity;
    private List<String> imgList;
    private int idMauSac;
    private int idKichCo;
    private int idProduct;
}
