package com.example.fploy.datn.model.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleUpdateRequest {

    private Integer id;

    @NotBlank(message = "Khong duoc de trong ten Role")
    private String name;

}
