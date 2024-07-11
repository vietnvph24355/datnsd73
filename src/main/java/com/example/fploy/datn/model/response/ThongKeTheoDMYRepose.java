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
@SqlResultSetMapping(
        name = "ThongKeTheoDMYMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ThongKeTheoDMYRepose.class,
                        columns = {
                                @ColumnResult(name = "tongDoanhThu", type = Long.class),
                                @ColumnResult(name = "tongSoDonThanhCong", type = Long.class),
                                @ColumnResult(name = "tongSoDonHuy", type = Long.class),
                                @ColumnResult(name = "tongSoSanPhamDaBan", type = Long.class),
                                @ColumnResult(name = "tongSoDonTaiQuay", type = Long.class),
                                @ColumnResult(name = "tongSoDonOnline", type = Long.class),
                        }
                )
        }
)
public class ThongKeTheoDMYRepose {
    private Long tongDoanhThu;

    private Long tongSoDonThanhCong;

    private Long tongSoDonHuy;

    private Long tongSoSanPhamDaBan;

    private Long tongSoDonTaiQuay;

    private Long tongSoDonOnline;
}
