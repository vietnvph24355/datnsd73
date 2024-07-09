package com.example.fploy.datn.model.request.create;

import com.example.fploy.datn.model.dto.ProductDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCreateRequest {
    private String avatar;
    private String name;
    private String descriptions;
    private Integer idNsx;
    private Integer idLoaiGiay;
    private Integer idChatLieu;
    private Integer idDeGiay;
    private Integer status;
    private List<ProductDetailDTO> productDetails;

}
