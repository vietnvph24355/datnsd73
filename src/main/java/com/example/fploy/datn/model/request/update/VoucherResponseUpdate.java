package com.example.fploy.datn.model.request.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherResponseUpdate {

    private Integer id;

    private Float giamGia;

    private BigDecimal donToiThieu;

    private BigDecimal giamToiDa;

    private Integer slApDung;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    private Integer status;

    private boolean cancelled;
}
