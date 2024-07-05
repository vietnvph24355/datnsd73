package com.example.fploy.datn.model.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenCreateRequest {

    private String tokens;

    private String tokensType;

    private Timestamp timeEnd;

    private Integer revoked;

    private Integer expired;

    private String userName;
}
