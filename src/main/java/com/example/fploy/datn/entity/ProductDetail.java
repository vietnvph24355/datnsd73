package com.example.fploy.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sl__da_ban")
    private Integer slDaBan;

    @Column(name = "img_list")
    private String imgList;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_create", nullable = false, updatable = false)
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_mau_sac", referencedColumnName = "id")
    private MauSac mauSac;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_kich_co", referencedColumnName = "id")
    private KichCo kichCo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;
}
