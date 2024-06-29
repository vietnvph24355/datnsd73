package com.example.fploy.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "oder_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sl_product")
    private Integer slProduct;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "oder_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;
}
