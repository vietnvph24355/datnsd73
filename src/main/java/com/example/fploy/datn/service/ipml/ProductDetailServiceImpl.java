package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.KichCo;
import com.example.fploy.datn.entity.MauSac;
import com.example.fploy.datn.entity.Product;
import com.example.fploy.datn.entity.ProductDetail;
import com.example.fploy.datn.model.dto.ProductDetailDTO;
import com.example.fploy.datn.model.request.create.ProductDetailCreateRequest;
import com.example.fploy.datn.model.request.update.ProductDetailUpdateRequest;
import com.example.fploy.datn.repository.KichCoRepository;
import com.example.fploy.datn.repository.MauSacRepository;
import com.example.fploy.datn.repository.ProductDetailRepository;
import com.example.fploy.datn.repository.ProductRepository;
import com.example.fploy.datn.service.ProductDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {


    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDetailDTO createProductDetail(ProductDetailCreateRequest request) {
        ProductDetail productDetail = new ProductDetail();
        mapProductDetailFromRequest(productDetail, request);
        productDetail.setDateCreate(new Date()); // Set ngày tạo là thời điểm hiện tại
        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
        return convertToDTO(savedProductDetail);
        //        ProductDetail productDetail = new ProductDetail();
//        mapProductDetailFromRequest(productDetail, request);
//        productDetail.setDateCreate(new Date());
//        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
//        return convertToDTO(savedProductDetail);
    }

    @Override
    public ProductDetailDTO updateProductDetail(ProductDetailUpdateRequest request) {
        ProductDetail productDetail = productDetailRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Chi tiết sản phẩm không tồn tại"));

        mapProductDetailFromRequest(productDetail, request);

        // Thêm ngày và giờ cập nhật
        productDetail.setDateUpdate(new Date());

        ProductDetail updatedProductDetail = productDetailRepository.save(productDetail);
        return convertToDTO(updatedProductDetail);
    }

    @Override
    public void deleteProductDetail(int id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public ProductDetailDTO getProductDetailById(int id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chi tiết sản phẩm không tồn tại"));
        return convertToDTO(productDetail);
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetails() {
        return productDetailRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDetailDTO convertToDTO(ProductDetail productDetail) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(productDetail.getId());

        // Xử lý thuộc tính cost
        if (productDetail.getCost() != null) {
            dto.setCost(productDetail.getCost().intValue());
        } else {
            dto.setCost(0); // Giá trị mặc định khi cost là null
        }

        // Xử lý thuộc tính quantity
        if (productDetail.getQuantity() != null) {
            dto.setQuantity(productDetail.getQuantity().intValue());
        } else {
            dto.setQuantity(0); // Giá trị mặc định khi quantity là null
        }

        // Xử lý thuộc tính slDaBan (số lượng đã bán)
        if (productDetail.getSlDaBan() != null) {
            dto.setSlDaBan(productDetail.getSlDaBan().intValue());
        } else {
            dto.setSlDaBan(0); // Giá trị mặc định khi slDaBan là null
        }

        // Xử lý thuộc tính imgList (danh sách hình ảnh)
        dto.setImgList(productDetail.getImgList());

        // Lấy ID của Màu sắc
        if (productDetail.getMauSac() != null) {
            dto.setIdMauSac(productDetail.getMauSac().getId());
        }

        // Lấy ID của Kích cỡ
        if (productDetail.getKichCo() != null) {
            dto.setIdKichCo(productDetail.getKichCo().getId());
        }

        // Lấy ID của Sản phẩm
        if (productDetail.getProduct() != null) {
            dto.setIdProduct(productDetail.getProduct().getId());
        }

        dto.setDateCreate(productDetail.getDateCreate());
        dto.setDateUpdate(productDetail.getDateUpdate());

        return dto;
    }

    private void mapProductDetailFromRequest(ProductDetail productDetail, ProductDetailCreateRequest request) {
        BeanUtils.copyProperties(request, productDetail);
        if (request.getImgList() != null && !request.getImgList().isEmpty()) {
            productDetail.setImgList(String.join(",", request.getImgList())); // Join images into a single string or handle as needed
        }
        setMauSac(productDetail, request.getIdMauSac());
        setKichCo(productDetail, request.getIdKichCo());
        setProduct(productDetail, request.getIdProduct());
    }

    private void mapProductDetailFromRequest(ProductDetail productDetail, ProductDetailUpdateRequest request) {
        BeanUtils.copyProperties(request, productDetail);
        setMauSac(productDetail, request.getIdMauSac());
        setKichCo(productDetail, request.getIdKichCo());
        setProduct(productDetail, request.getIdProduct());
    }

    private void setMauSac(ProductDetail productDetail, Integer mauSacId) {
        if (mauSacId != null) {
            Optional<MauSac> optionalMauSac = mauSacRepository.findById(mauSacId);
            if (optionalMauSac.isPresent()) {
                productDetail.setMauSac(optionalMauSac.get());
            } else {
                throw new RuntimeException("Màu sắc không tồn tại");
            }
        } else {
            productDetail.setMauSac(null);
        }
    }

    private void setKichCo(ProductDetail productDetail, Integer kichCoId) {
        if (kichCoId != null) {
            Optional<KichCo> optionalKichCo = kichCoRepository.findById(kichCoId);
            if (optionalKichCo.isPresent()) {
                productDetail.setKichCo(optionalKichCo.get());
            } else {
                throw new RuntimeException("Kích cỡ không tồn tại");
            }
        } else {
            productDetail.setKichCo(null);
        }
    }

    private void setProduct(ProductDetail productDetail, Integer productId) {
        if (productId != null) {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                productDetail.setProduct(optionalProduct.get());
            } else {
                throw new RuntimeException("Sản phẩm không tồn tại");
            }
        } else {
            productDetail.setProduct(null);
        }
    }

//
//
//    @Autowired
//    private ProductDetailRepository productDetailRepository;
//
//    @Autowired
//    private MauSacRepository mauSacRepository;
//
//    @Autowired
//    private KichCoRepository kichCoRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public ProductDetailDTO createProductDetail(ProductDetailCreateRequest request) {
//        ProductDetail productDetail = new ProductDetail();
//        mapProductDetailFromRequest(productDetail, request);
//        productDetail.setDateCreate(new Date());
//        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
//        return convertToDTO(savedProductDetail);
//    }
//
//    @Override
//    public ProductDetailDTO updateProductDetail(ProductDetailUpdateRequest request) {
//        ProductDetail productDetail = productDetailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Chi tiết sản phẩm không tồn tại"));
//
//        mapProductDetailFromRequest(productDetail, request);
//
//        ProductDetail updatedProductDetail = productDetailRepository.save(productDetail);
//        return convertToDTO(updatedProductDetail);
//    }
//
//    @Override
//    public void deleteProductDetail(int id) {
//        productDetailRepository.deleteById(id);
//    }
//
//    @Override
//    public ProductDetailDTO getProductDetailById(int id) {
//        ProductDetail productDetail = productDetailRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Chi tiết sản phẩm không tồn tại"));
//        return convertToDTO(productDetail);
//    }
//
//    @Override
//    public List<ProductDetailDTO> getAllProductDetails() {
//        return productDetailRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private ProductDetailDTO convertToDTO(ProductDetail productDetail) {
//        ProductDetailDTO dto = new ProductDetailDTO();
//        dto.setId(productDetail.getId());
//
//        // Xử lý thuộc tính cost
//        if (productDetail.getCost() != null) {
//            dto.setCost(productDetail.getCost().intValue());
//        } else {
//            dto.setCost(0); // Giá trị mặc định khi cost là null
//        }
//
//        // Xử lý thuộc tính quantity
//        if (productDetail.getQuantity() != null) {
//            dto.setQuantity(productDetail.getQuantity().intValue());
//        } else {
//            dto.setQuantity(0); // Giá trị mặc định khi quantity là null
//        }
//
//        // Xử lý thuộc tính slDaBan (số lượng đã bán)
//        if (productDetail.getSlDaBan() != null) {
//            dto.setSlDaBan(productDetail.getSlDaBan().intValue());
//        } else {
//            dto.setSlDaBan(0); // Giá trị mặc định khi slDaBan là null
//        }
//
//        // Xử lý thuộc tính imgList (danh sách hình ảnh)
//        dto.setImgList(productDetail.getImgList());
//
//        // Lấy ID của Màu sắc
//        if (productDetail.getMauSac() != null) {
//            dto.setIdMauSac(productDetail.getMauSac().getId());
//        }
//
//        // Lấy ID của Kích cỡ
//        if (productDetail.getKichCo() != null) {
//            dto.setIdKichCo(productDetail.getKichCo().getId());
//        }
//
//        // Lấy ID của Sản phẩm
//        if (productDetail.getProduct() != null) {
//            dto.setIdProduct(productDetail.getProduct().getId());
//        }
//
//        dto.setDateCreate(productDetail.getDateCreate());
//        dto.setDateUpdate(productDetail.getDateUpdate());
//
//        return dto;
//    }
//
//    private void mapProductDetailFromRequest(ProductDetail productDetail, ProductDetailCreateRequest request) {
//        BeanUtils.copyProperties(request, productDetail);
//        if (request.getImgList() != null && !request.getImgList().isEmpty()) {
//            productDetail.setImgList(String.join(",", request.getImgList())); // Join images into a single string or handle as needed
//        }
//        setMauSac(productDetail, request.getIdMauSac());
//        setKichCo(productDetail, request.getIdKichCo());
//        setProduct(productDetail, request.getIdProduct());
//    }
//
//    private void mapProductDetailFromRequest(ProductDetail productDetail, ProductDetailUpdateRequest request) {
//        BeanUtils.copyProperties(request, productDetail);
//        setMauSac(productDetail, request.getIdMauSac());
//        setKichCo(productDetail, request.getIdKichCo());
//        setProduct(productDetail, request.getIdProduct());
//    }
//
//    private void setMauSac(ProductDetail productDetail, Integer mauSacId) {
//        if (mauSacId != null) {
//            Optional<MauSac> optionalMauSac = mauSacRepository.findById(mauSacId);
//            if (optionalMauSac.isPresent()) {
//                productDetail.setMauSac(optionalMauSac.get());
//            } else {
//                throw new RuntimeException("Màu sắc không tồn tại");
//            }
//        } else {
//            productDetail.setMauSac(null);
//        }
//    }
//
//    private void setKichCo(ProductDetail productDetail, Integer kichCoId) {
//        if (kichCoId != null) {
//            Optional<KichCo> optionalKichCo = kichCoRepository.findById(kichCoId);
//            if (optionalKichCo.isPresent()) {
//                productDetail.setKichCo(optionalKichCo.get());
//            } else {
//                throw new RuntimeException("Kích cỡ không tồn tại");
//            }
//        } else {
//            productDetail.setKichCo(null);
//        }
//    }
//
//    private void setProduct(ProductDetail productDetail, Integer productId) {
//        if (productId != null) {
//            Optional<Product> optionalProduct = productRepository.findById(productId);
//            if (optionalProduct.isPresent()) {
//                productDetail.setProduct(optionalProduct.get());
//            } else {
//                throw new RuntimeException("Sản phẩm không tồn tại");
//            }
//        } else {
//            productDetail.setProduct(null);
//        }
//    }
}
