package com.example.fploy.datn.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDetailDTO {
    private int id;
    private int cost;
    private int quantity;
    private int slDaBan;
    private String imgList;
    private int idMauSac;
    private int idKichCo;
    private int idProduct;
    private Date dateCreate;
    private Date dateUpdate;
}
