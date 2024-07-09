package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Khach Hang
    @Query("""
                SELECT u.id,
                u.name,
                u.gmail,
                u.phone,
                u.password,
                u.gender,
                u.dateOfBirth,
                u.diaChi,
                r.name as nameRole,
                u.isActivate
                FROM User u
                INNER JOIN Role r
                ON u.role.id = r.id
                WHERE (u.name LIKE %:searchText% OR u.gmail LIKE %:searchText% OR  u.phone LIKE %:searchText% )
                AND (:searchGender IS NULL OR u.gender = :searchGender)
                AND (:searchStatus IS NULL OR u.isActivate = :searchStatus)
                AND r.id = 2              
                    """)
    Page<User> findByAllClient(Pageable pageable,
                               @Param("searchText") String searchText,
                               @Param("searchStatus")Boolean searchStatus,
                               @Param("searchGender")Boolean searchGender);


    // Admin
    @Query(value = """
                SELECT u.id,
                u.name,
                u.gmail,
                u.phone,
                u.password,
                u.gender,
                u.dateOfBirth,
                u.diaChi,
                r.name as nameRole,
                u.isActivate
                FROM User u
                INNER JOIN Role r
                ON u.role.id = r.id
                WHERE (u.name LIKE %:searchText% OR u.gmail LIKE %:searchText% OR  u.phone LIKE %:searchText% )
                AND (:searchGender IS NULL OR u.gender = :searchGender)
                AND (:searchStatus IS NULL OR u.isActivate = :searchStatus)
                AND r.id = 1
                    """)
    Page<User> findByAllAdmin(Pageable pageable,
                                      @Param("searchText") String searchText,
                                      @Param("searchStatus")Boolean searchStatus,
                                      @Param("searchGender")Boolean searchGender);


    // Nhan Vien
    @Query(value = """
                SELECT u.id,
                u.name,
                u.gmail,
                u.phone,
                u.password,
                u.gender,
                u.dateOfBirth,
                u.diaChi,
                r.name as nameRole,
                u.isActivate
                FROM User u
                INNER JOIN Role r
                ON u.role.id = r.id
                WHERE (u.name LIKE %:searchText% OR u.gmail LIKE %:searchText% OR  u.phone LIKE %:searchText% )
                AND (:searchGender IS NULL OR u.gender = :searchGender)
                AND (:searchStatus IS NULL OR u.isActivate = :searchStatus)
                AND r.id = 3                     
                    """)
    Page<User> findByAllStaff(Pageable pageable,
                                      @Param("searchText") String searchText,
                                      @Param("searchStatus")Boolean searchStatus,
                                      @Param("searchGender")Boolean searchGender);



    // Tim theo ten
    @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.id =:id")
    User findId(@Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.gmail =:gmail")
    Optional<User> findGmail(String gmail);

    @Query("SELECT u FROM User u WHERE u.gmail =:gmail")
    User findGmail1(String gmail);

    User findUserByGmail(String gmail);

    boolean existsByGmail(String gmail);

    @Query("SELECT s FROM User s " +
            "WHERE s.role.id = 3")
    List<User> findAllNhanVienExcel();

    @Query("SELECT s FROM User s " +
            "WHERE s.role.id = 2")
    List<User> findAllClientExcel();


}
