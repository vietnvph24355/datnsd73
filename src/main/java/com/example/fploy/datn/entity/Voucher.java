package com.example.fploy.datn.entity;

import com.example.fploy.datn.entity.trangThai.TrangThaiVoucher;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "giam_gia")
    private Float giaTriGiam;

    @Column(name = "don_toi_Thieu")
    private BigDecimal donToiThieu;

    @Column(name = "giam_toi_da")
    private BigDecimal giamToiDa;

    @Column(name = "sl_ap_dung")
    private Integer slApDung;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TrangThaiVoucher status;

    //
    @JsonManagedReference
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL)
    private List<Order> orders;


}
