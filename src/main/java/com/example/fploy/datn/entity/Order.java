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
@Table(name = "Oder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "sdt_user")
    private Integer sdtUser;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "Note")
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createAt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User users;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_voucher", referencedColumnName = "id")
    private Voucher voucher;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

}
