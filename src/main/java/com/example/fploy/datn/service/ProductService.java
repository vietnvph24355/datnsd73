package com.example.fploy.datn.service;

import com.example.fploy.datn.model.dto.ProductDTO;
import com.example.fploy.datn.model.request.create.ProductCreateRequest;
import com.example.fploy.datn.model.request.update.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductCreateRequest request);
    ProductDTO updateProduct(ProductUpdateRequest request);
    void deleteProduct(int id);
    ProductDTO getProductById(int id);
    List<ProductDTO> getAllProducts();
}
