package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r.id, r.name FROM Role r WHERE (:searchText is null or r.name LIKE %:searchText)")
    Page<Role> findByAll(Pageable pageable, @Param("searchText") String searchText);



    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.id =:id")
    Role findId(@Param("id") Integer id);
}
