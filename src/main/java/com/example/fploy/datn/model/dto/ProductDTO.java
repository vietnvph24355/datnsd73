package com.example.fploy.datn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String avatar;
    private String name;
    private String descriptions;
    private Date dateCreate;
    private Date dateUpdate;
    private Integer status;
    private Integer idNsx;
    private Integer idLoaiGiay;
    private Integer idChatLieu;
    private Integer idDeGiay;
}
