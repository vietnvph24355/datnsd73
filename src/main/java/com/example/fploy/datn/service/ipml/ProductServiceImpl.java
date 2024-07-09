package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.*;
import com.example.fploy.datn.model.dto.ProductDTO;
import com.example.fploy.datn.model.dto.ProductDetailDTO;
import com.example.fploy.datn.model.request.create.ProductCreateRequest;
import com.example.fploy.datn.model.request.update.ProductUpdateRequest;
import com.example.fploy.datn.repository.*;
import com.example.fploy.datn.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NSXRepository nsxRepository;

    @Autowired
    private LoaiGiayRepository loaiGiayRepository;

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Autowired
    private DeGiayRepository deGiayRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductDTO createProduct(ProductCreateRequest request) {
        if (request.getIdNsx() == null || request.getIdNsx() <= 0) {
            throw new IllegalArgumentException("Invalid NSX ID: " + request.getIdNsx());
        }

        Product product = new Product();
        mapProductFromCreateRequest(product, request);
        product.setDateCreate(new Date()); // Set current date/time for creation
        productRepository.save(product);
        return convertToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductUpdateRequest request) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        mapProductFromRequest(product, request);
        product.setDateUpdate(new Date()); // Set current date/time for update
        saveOrUpdateProductDetails(product, request.getProductDetails());

        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductDetail> productDetails = product.getProductDetails();
        if (productDetails != null && !productDetails.isEmpty()) {
            productDetails.forEach(productDetailRepository::delete);
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void mapProductFromRequest(Product product, ProductUpdateRequest request) {
        product.setAvatar(request.getAvatar());
        product.setName(request.getName());
        product.setDescriptions(request.getDescriptions());
        product.setStatus(request.getStatus());

        setEntityFields(product, request.getIdNsx(), nsxRepository, "NSX");
        setEntityFields(product, request.getIdLoaiGiay(), loaiGiayRepository, "Loai Giay");
        setEntityFields(product, request.getIdChatLieu(), chatLieuRepository, "Chat Lieu");
        setEntityFields(product, request.getIdDeGiay(), deGiayRepository, "De Giay");
    }

    private void setEntityFields(Product product, Integer id, JpaRepository<?, Integer> repository, String entityName) {
        if (id != null && id != 0) {
            Optional<?> optionalEntity = repository.findById(id);
            if (optionalEntity.isPresent()) {
                Object entity = optionalEntity.get();
                if (entity instanceof NSX) {
                    product.setNsx((NSX) entity);
                } else if (entity instanceof LoaiGiay) {
                    product.setLoaiGiay((LoaiGiay) entity);
                } else if (entity instanceof ChatLieu) {
                    product.setChatLieu((ChatLieu) entity);
                } else if (entity instanceof DeGiay) {
                    product.setDeGiay((DeGiay) entity);
                } else {
                    throw new RuntimeException("Unsupported entity type: " + entityName);
                }
            } else {
                throw new RuntimeException(entityName + " not found with id: " + id);
            }
        }
    }


    private void saveOrUpdateProductDetails(Product product, List<ProductDetailDTO> productDetailsDTO) {
        if (productDetailsDTO != null && !productDetailsDTO.isEmpty()) {
            List<ProductDetail> productDetails = productDetailsDTO.stream()
                    .map(dto -> {
                        ProductDetail productDetail = new ProductDetail();
                        productDetail.setCost(dto.getCost());
                        productDetail.setQuantity(dto.getQuantity());
                        // Set other fields as needed
                        return productDetail;
                    })
                    .collect(Collectors.toList());

            product.setProductDetails(productDetails);
        }
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setAvatar(product.getAvatar());
        dto.setName(product.getName());
        dto.setDescriptions(product.getDescriptions());
        dto.setDateCreate(product.getDateCreate());
        dto.setDateUpdate(product.getDateUpdate());
        dto.setStatus(product.getStatus());

        if (product.getNsx() != null) {
            dto.setIdNsx(product.getNsx().getId());
        }

        if (product.getLoaiGiay() != null) {
            dto.setIdLoaiGiay(product.getLoaiGiay().getId());
        }

        if (product.getChatLieu() != null) {
            dto.setIdChatLieu(product.getChatLieu().getId());
        }

        if (product.getDeGiay() != null) {
            dto.setIdDeGiay(product.getDeGiay().getId());
        }

        return dto;
    }

    private void mapProductFromCreateRequest(Product product, ProductCreateRequest request) {
        product.setAvatar(request.getAvatar());
        product.setName(request.getName());
        product.setDescriptions(request.getDescriptions());
        product.setDateCreate(new Date());
        product.setDateUpdate(null);
        product.setStatus(request.getStatus());

        setEntityFields(product, request.getIdNsx(), nsxRepository, "NSX");
        setEntityFields(product, request.getIdLoaiGiay(), loaiGiayRepository, "Loai Giay");
        setEntityFields(product, request.getIdChatLieu(), chatLieuRepository, "Chat Lieu");
        setEntityFields(product, request.getIdDeGiay(), deGiayRepository, "De Giay");

        saveOrUpdateProductDetails(product, request.getProductDetails());
    }
}
