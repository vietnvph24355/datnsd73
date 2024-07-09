package com.example.fploy.datn.service;

import com.example.fploy.datn.model.dto.ProductDetailDTO;
import com.example.fploy.datn.model.request.create.ProductDetailCreateRequest;
import com.example.fploy.datn.model.request.update.ProductDetailUpdateRequest;

import java.util.List;

public interface ProductDetailService {
    ProductDetailDTO createProductDetail(ProductDetailCreateRequest request);
    ProductDetailDTO updateProductDetail(ProductDetailUpdateRequest request);
    void deleteProductDetail(int id);
    ProductDetailDTO getProductDetailById(int id);
    List<ProductDetailDTO> getAllProductDetails();
}
