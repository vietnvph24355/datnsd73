package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.SocialAccount;
import com.example.fploy.datn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Integer> {

    @Query("""
        SELECT s FROM SocialAccount s  
        WHERE (s.name LIKE %:searchText% OR s.email LIKE %:searchText% OR s.users.name LIKE %:serachText%)
""")
    Page<SocialAccount> getAll(Pageable pageable, @Param("searchText")String searchText);

    @Query("""  
            select s FROM SocialAccount s where s.id = :id
""")
    SocialAccount getById(@Param("id")Integer id);

    // Tim theo ten
    @Query("SELECT s FROM SocialAccount s WHERE s.name = :name")
    Optional<SocialAccount> findByName(String name);
}
