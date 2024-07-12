package com.example.fploy.datn.entity.trangThai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StatusSerializer.class)

public enum TrangThaiVoucher {

        @JsonProperty("ACTIVE")
        ACTIVE,
        UPCOMING,
        ONGOING,
        ENDING_SOON,
        EXPIRED,
        OUT_OF_STOCK,
        CANCELLED

}
