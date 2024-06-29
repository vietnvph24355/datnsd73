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
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_create", nullable = false, updatable = false)
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_nsx", referencedColumnName = "id")
    private NSX nsx;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_loai_giay", referencedColumnName = "id")
    private LoaiGiay loaiGiay;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_chat_lieu", referencedColumnName = "id")
    private ChatLieu chatLieu;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_de_giay", referencedColumnName = "id")
    private DeGiay deGiay;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
