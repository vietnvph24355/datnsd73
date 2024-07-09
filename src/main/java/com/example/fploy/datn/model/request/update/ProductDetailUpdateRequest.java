package com.example.fploy.datn.model.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailUpdateRequest {
    private int id;
    private int cost;
    private int quantity;
    private String imgList;
    private int idMauSac;
    private int idKichCo;
    private int idProduct;
}
