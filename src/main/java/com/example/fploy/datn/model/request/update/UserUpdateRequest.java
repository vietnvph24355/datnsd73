package com.example.fploy.datn.model.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {

    private Integer id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
    private String gmail;

    @NotBlank
    @Size(min = 10, max = 11)
    @Pattern(regexp = "^0\\d{9,10}$", message = "Phone number must start with '0' and have 10 or 11 digits")
    private String phone;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;


    private Boolean gender;

    @NotNull
    private Date dateOfBirth;

    private String google;

    private String facebook;

    @NotBlank
    @Size(max = 1000)
    private String diaChi;


    private Integer roleId; // Id của vai trò

    @NotBlank
    private String roleName; // Tên của vai trò
}
