package com.example.fploy.datn.model.request.create;

import com.example.fploy.datn.entity.trangThai.TrangThaiVoucher;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherResponseCreate {

    //private Integer id;

    private String ma;

    private Integer slApDung;

    private Float giamGia;

    private BigDecimal donToiThieu;

    private BigDecimal giamToiDa;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    private TrangThaiVoucher status;
}
