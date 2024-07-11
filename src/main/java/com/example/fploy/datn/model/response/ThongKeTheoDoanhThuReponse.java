package com.example.fploy.datn.model.response;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "ThongKeTheoDoanhThuMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ThongKeTheoDoanhThuReponse.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "ten", type = String.class),
                                @ColumnResult(name = "soLuongDaBan", type = Integer.class),
                                @ColumnResult(name = "doanhThu", type = BigDecimal.class)
                        }
                )
        }
)
public class ThongKeTheoDoanhThuReponse {

    private Integer id;

    private String ten;

    private Integer soLuongDaBan;

    private BigDecimal doanhThu;

}
