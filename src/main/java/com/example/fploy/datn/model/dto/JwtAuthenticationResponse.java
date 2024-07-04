package com.example.fploy.datn.model.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String tokens;

    private String refreshToken;

    private String gmail;

    private String name;

    private Integer roleId;

    private Integer acountId;
}
