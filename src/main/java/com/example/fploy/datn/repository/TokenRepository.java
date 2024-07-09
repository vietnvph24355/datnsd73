package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.Token;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(""" 
            SELECT t FROM Token t 
            WHERE (t.tokens like %:searchText% OR t.tokensType like %:searchText% OR t.users.name like %:searchText%)
""")
    Page<Token> getAll(Pageable pageable, @Param("searchText")String text);

    @Query("select t from Token t where t.id =: id")
    Optional<Token> findById(Integer id);

//    @Query("SELECT t FROM Token t WHERE t.expired != 2 and t.revoked != 1")
//    List<Token> TokenKhongHuy();
}
