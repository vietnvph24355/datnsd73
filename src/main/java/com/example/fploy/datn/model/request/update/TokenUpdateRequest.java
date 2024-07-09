package com.example.fploy.datn.model.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenUpdateRequest {

    private Integer id;

    @NotBlank
    private String tokens;
    @NotBlank
    private String tokensType;
    //@NotBlank
    private Timestamp timeEnd;

    private Integer revoked;

    private Integer expired;
    @NotBlank
    private String userName;
}
