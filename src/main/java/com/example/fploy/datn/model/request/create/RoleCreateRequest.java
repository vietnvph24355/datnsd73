package com.example.fploy.datn.model.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleCreateRequest {

    @NotBlank(message = "Khong duoc de trong ten Role")
    private String name;

}
