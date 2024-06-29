package com.example.fploy.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

///**
// * @author VietNV
// */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tokens")
    private String tokens;

    @Column(name = "tokens_type")
    private String tokensType;

    @Column(name = "time_end")
    private LocalDate timeEnd;

    @Column(name = "revoked") // Bi thu hoi
    private Integer revoked;

    @Column(name = "expired") // Bi huy
    private Integer expired;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

}
