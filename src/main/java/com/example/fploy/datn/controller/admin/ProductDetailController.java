package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.dto.ProductDetailDTO;
import com.example.fploy.datn.model.request.create.ProductDetailCreateRequest;
import com.example.fploy.datn.model.request.update.ProductDetailUpdateRequest;
import com.example.fploy.datn.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping
    public ProductDetailDTO createProductDetail(@RequestBody ProductDetailCreateRequest request) {
        return productDetailService.createProductDetail(request);
    }

    @PutMapping("/{id}")
    public ProductDetailDTO updateProductDetail(@PathVariable int id, @RequestBody ProductDetailUpdateRequest request) {
        request.setId(id);
        return productDetailService.updateProductDetail(request);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable int id) {
        productDetailService.deleteProductDetail(id);
    }

    @GetMapping("/{id}")
    public ProductDetailDTO getProductDetailById(@PathVariable int id) {
        return productDetailService.getProductDetailById(id);
    }

    @GetMapping
    public List<ProductDetailDTO> getAllProductDetails() {
        return productDetailService.getAllProductDetails();
    }
}
