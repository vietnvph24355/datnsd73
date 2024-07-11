package com.example.fploy.datn.model.response;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(  name = "ThongKeSoLuongTonResponse",
        classes = {
                @ConstructorResult(
                        targetClass = ThongKeTheoSoLuongTon.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "ten", type = String.class),
                                @ColumnResult(name = "soLuongTon", type = Integer.class)
                        }
                )
        })
public class ThongKeTheoSoLuongTon {
    private Integer id;

    private String ten;

    private Integer soLuongTon;

}
